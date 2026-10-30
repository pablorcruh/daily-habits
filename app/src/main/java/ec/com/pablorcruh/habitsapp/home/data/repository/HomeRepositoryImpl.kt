package ec.com.pablorcruh.habitsapp.home.data.repository

import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import ec.com.pablorcruh.habitsapp.home.data.extension.toStartOfDateTimestamp
import ec.com.pablorcruh.habitsapp.home.data.local.HomeDao
import ec.com.pablorcruh.habitsapp.home.data.local.entity.HabitSyncEntity
import ec.com.pablorcruh.habitsapp.home.data.mapper.toDomain
import ec.com.pablorcruh.habitsapp.home.data.mapper.toDto
import ec.com.pablorcruh.habitsapp.home.data.mapper.toEntity
import ec.com.pablorcruh.habitsapp.home.data.mapper.toSyncEntity
import ec.com.pablorcruh.habitsapp.home.data.remote.dto.HomeApi
import ec.com.pablorcruh.habitsapp.home.data.remote.util.resultOf
import ec.com.pablorcruh.habitsapp.home.data.sync.HabitSyncWorker
import ec.com.pablorcruh.habitsapp.home.domain.alarm.AlarmHandler
import ec.com.pablorcruh.habitsapp.home.domain.models.Habit
import ec.com.pablorcruh.habitsapp.home.domain.repository.HomeRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.time.Duration
import java.time.ZonedDateTime

class HomeRepositoryImpl(
    private val dao: HomeDao,
    private val api: HomeApi,
    private val alarmHandler: AlarmHandler,
    private val workManager: WorkManager
): HomeRepository {

    override fun getAllHabitsSelectedDate(date: ZonedDateTime): Flow<List<Habit>> {
        val localFlow = dao.getAllHabitsForSelectedDate(date.toStartOfDateTimestamp()).map {
           it.map {
               it.toDomain()
           }
        }

        val apiFlow = getHabitsFromApi()

        return localFlow.combine(apiFlow){ db, _ ->
            db
        }
    }

    private fun getHabitsFromApi(): Flow<List<Habit>> {
        return flow{
            resultOf{
                val habits = api.getAllHabits().toDomain()
                insertHabits(habits)
            }
            emit(emptyList<Habit>())
        }.onStart {
            emit(emptyList())
        }

    }

    private suspend fun insertHabits(habits:List<Habit>){
        habits.forEach {
            handleAlarm(it)
            dao.insertHabit(it.toEntity())
        }
    }

    override suspend fun insertHabit(habit: Habit) {
        handleAlarm(habit)
        dao.insertHabit(habit.toEntity())
        resultOf{
            api.insertHabit(habit.toDto())
        }.onFailure {
            dao.insertHabitSync(habit.toSyncEntity())
        }
    }

    override suspend fun getHabitById(id: String): Habit {
        return dao.getHabitById(id).toDomain()
    }

    override suspend fun syncHabits() {
        val worker = OneTimeWorkRequestBuilder<HabitSyncWorker>().setConstraints(
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        ).setBackoffCriteria(BackoffPolicy.EXPONENTIAL, Duration.ofMinutes(5)).build()
        workManager.beginUniqueWork("sync_habit_id", ExistingWorkPolicy.REPLACE, worker).enqueue()
    }

    private suspend fun handleAlarm(habit: Habit){
        try{
            val previous = dao.getHabitById(habit.id)
            alarmHandler.cancel(previous.toDomain())
        }catch(e: Exception){}
        alarmHandler.setRecurringAlarm(habit)
    }
}
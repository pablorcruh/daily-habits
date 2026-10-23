package ec.com.pablorcruh.habitsapp.home.data.repository

import ec.com.pablorcruh.habitsapp.home.data.extension.toStartOfDateTimestamp
import ec.com.pablorcruh.habitsapp.home.data.local.HomeDao
import ec.com.pablorcruh.habitsapp.home.data.mapper.toDomain
import ec.com.pablorcruh.habitsapp.home.data.mapper.toDto
import ec.com.pablorcruh.habitsapp.home.data.mapper.toEntity
import ec.com.pablorcruh.habitsapp.home.data.remote.dto.HomeApi
import ec.com.pablorcruh.habitsapp.home.data.remote.util.resultOf
import ec.com.pablorcruh.habitsapp.home.domain.models.Habit
import ec.com.pablorcruh.habitsapp.home.domain.repository.HomeRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.time.ZonedDateTime

class HomeRepositoryImpl(
    private val dao: HomeDao,
    private val api: HomeApi
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
        dao.insertHabits(habits.map{it.toEntity()})
    }

    override suspend fun insertHabit(habit: Habit) {
        dao.insertHabit(habit.toEntity())
        resultOf{
            api.insertHabit(habit.toDto())
        }
    }

    override suspend fun getHabitById(id: String): Habit {
        return dao.getHabitById(id)!!.toDomain()
    }
}
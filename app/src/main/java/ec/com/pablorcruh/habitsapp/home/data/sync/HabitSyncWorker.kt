package ec.com.pablorcruh.habitsapp.home.data.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import ec.com.pablorcruh.habitsapp.home.data.local.HomeDao
import ec.com.pablorcruh.habitsapp.home.data.local.entity.HabitSyncEntity
import ec.com.pablorcruh.habitsapp.home.data.mapper.toDomain
import ec.com.pablorcruh.habitsapp.home.data.mapper.toDto
import ec.com.pablorcruh.habitsapp.home.data.remote.dto.HomeApi
import ec.com.pablorcruh.habitsapp.home.data.remote.util.resultOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

@HiltWorker
class HabitSyncWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted val workerParams: WorkerParameters,
    private val api: HomeApi,
    private val dao: HomeDao
): CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        if(runAttemptCount >=3){
            return Result.failure()
        }
        val items = dao.getAllHabitsSync()
        return try {
            supervisorScope {
                val jobs = items.map {item -> launch{sync(item)}}
                jobs.forEach{it.join()}
            }
            Result.success()
        }catch(e: Exception){
            Result.retry()
        }
    }

    private suspend fun sync(entity: HabitSyncEntity){
        val habit = dao.getHabitById(entity.id).toDomain().toDto()
        resultOf {
            api.insertHabit(habit)
        }.onSuccess {
            dao.deleteHabitSync(entity)
        }.onFailure {
            throw it
        }
    }
}
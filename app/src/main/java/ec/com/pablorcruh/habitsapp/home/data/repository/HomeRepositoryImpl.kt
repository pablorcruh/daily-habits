package ec.com.pablorcruh.habitsapp.home.data.repository

import ec.com.pablorcruh.habitsapp.home.data.extension.toStartOfDateTimestamp
import ec.com.pablorcruh.habitsapp.home.data.local.HomeDao
import ec.com.pablorcruh.habitsapp.home.data.mapper.toDomain
import ec.com.pablorcruh.habitsapp.home.data.mapper.toEntity
import ec.com.pablorcruh.habitsapp.home.domain.models.Habit
import ec.com.pablorcruh.habitsapp.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.ZonedDateTime

class HomeRepositoryImpl(
    private val dao: HomeDao
): HomeRepository {
    override fun getAllHabitsSelectedDate(date: ZonedDateTime): Flow<List<Habit>> {
       return dao.getAllHabitsForSelectedDate(date.toStartOfDateTimestamp()).map {
           it.map {
               it.toDomain()
           }
       }
    }

    override suspend fun insertHabit(habit: Habit) {
        dao.insertHabit(habit.toEntity())
    }

    override suspend fun getHabitById(id: String): Habit {
        return dao.getHabitById(id)!!.toDomain()
    }
}
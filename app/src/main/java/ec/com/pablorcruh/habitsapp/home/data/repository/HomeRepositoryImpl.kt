package ec.com.pablorcruh.habitsapp.home.data.repository

import ec.com.pablorcruh.habitsapp.home.domain.models.Habit
import ec.com.pablorcruh.habitsapp.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import java.time.ZonedDateTime

class HomeRepositoryImpl: HomeRepository {
    override fun getAllHabitsSelectedDate(date: ZonedDateTime): Flow<List<Habit>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertHabit(habit: Habit) {
        TODO("Not yet implemented")
    }
}
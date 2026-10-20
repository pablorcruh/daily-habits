package ec.com.pablorcruh.habitsapp.home.domain.repository

import ec.com.pablorcruh.habitsapp.home.domain.models.Habit
import kotlinx.coroutines.flow.Flow
import java.time.ZonedDateTime

interface HomeRepository {

    fun getAllHabitsSelectedDate(date: ZonedDateTime): Flow<List<Habit>>

    suspend fun insertHabit(habit: Habit)
}
package ec.com.pablorcruh.habitsapp.home.domain.home.useCases

import ec.com.pablorcruh.habitsapp.home.domain.models.Habit
import ec.com.pablorcruh.habitsapp.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import java.time.ZonedDateTime

class GetHabitsForDateUseCase(
    private val repository: HomeRepository
) {

    operator fun invoke(date: ZonedDateTime): Flow<List<Habit>> {
        return repository.getAllHabitsSelectedDate(date)
    }
}
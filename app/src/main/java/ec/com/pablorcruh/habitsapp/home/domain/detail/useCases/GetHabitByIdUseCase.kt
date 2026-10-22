package ec.com.pablorcruh.habitsapp.home.domain.detail.useCases

import ec.com.pablorcruh.habitsapp.home.domain.models.Habit
import ec.com.pablorcruh.habitsapp.home.domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetHabitByIdUseCase(
    private val repository: HomeRepository
) {

    suspend operator fun invoke(id: String): Habit{
        return withContext(Dispatchers.IO){
            repository.getHabitById(id);
        }
    }
}
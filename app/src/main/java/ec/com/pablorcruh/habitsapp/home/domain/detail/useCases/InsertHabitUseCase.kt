package ec.com.pablorcruh.habitsapp.home.domain.detail.useCases

import ec.com.pablorcruh.habitsapp.home.domain.models.Habit
import ec.com.pablorcruh.habitsapp.home.domain.repository.HomeRepository

class InsertHabitUseCase(
    private val respository: HomeRepository
) {

    suspend operator fun invoke(habit: Habit){
        respository.insertHabit(habit)
    }
}
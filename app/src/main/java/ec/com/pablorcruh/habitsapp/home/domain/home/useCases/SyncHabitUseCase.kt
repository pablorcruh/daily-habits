package ec.com.pablorcruh.habitsapp.home.domain.home.useCases

import ec.com.pablorcruh.habitsapp.home.domain.repository.HomeRepository

class SyncHabitUseCase(private val  repository: HomeRepository) {

    suspend operator fun invoke(){
        repository.syncHabits()
    }
}
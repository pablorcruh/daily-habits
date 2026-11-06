package ec.com.pablorcruh.habitsapp.authentication.domain.usecase

import ec.com.pablorcruh.habitsapp.authentication.domain.repository.AuthenticationRepository

class LogoutUseCase(private val repository: AuthenticationRepository) {

    suspend operator fun invoke(){
        repository.logout()
    }
}
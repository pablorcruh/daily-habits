package ec.com.pablorcruh.habitsapp.authentication.domain.usecase

import ec.com.pablorcruh.habitsapp.authentication.domain.repository.AuthenticationRepository

class LoginWithEmailUseCase(private val authenticationRepository: AuthenticationRepository) {

    suspend operator fun invoke(email: String, password: String): Result<Unit>{
        return authenticationRepository.login(email, password)
    }
}
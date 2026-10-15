package ec.com.pablorcruh.habitsapp.authentication.domain.usecase

import ec.com.pablorcruh.habitsapp.authentication.domain.repository.AuthenticationRepository

class SignupWithEmailUseCase(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Unit>{
        return repository.signUp(email, password)
    }
}
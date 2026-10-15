package ec.com.pablorcruh.habitsapp.authentication.domain.usecase

data class LoginUseCases(
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase,
    val loginWithEmailUseCase: LoginWithEmailUseCase
)

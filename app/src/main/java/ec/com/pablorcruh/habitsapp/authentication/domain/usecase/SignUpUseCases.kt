package ec.com.pablorcruh.habitsapp.authentication.domain.usecase

data class SignUpUseCases(
    val validateEmail: ValidateEmailUseCase,
    val validatePassword: ValidatePasswordUseCase,
    val signupWithEmail: SignupWithEmailUseCase
)
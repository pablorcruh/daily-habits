package ec.com.pablorcruh.habitsapp.authentication.domain.usecase

class ValidatePasswordUseCase {
    operator fun invoke(password: String): PasswordResult{
        if(password.length < 8){
            return PasswordResult.Invalid("El password debe tener 8 caracteres")
        }

        if(!password.any { it.isDigit() }){
            return PasswordResult.Invalid("El password debe tener 1 numero")
        }
        if(!password.any{it.isUpperCase()}){
            return PasswordResult.Invalid("El password debe tener 1 caracteres mayuscula")
        }
        if(!password.any{it.isLowerCase()}){
            return PasswordResult.Invalid("El password debe tener 1 caracteres minuscula")
        }
        return PasswordResult.Valid
    }
}

sealed class PasswordResult{
    object Valid: PasswordResult()
    data class Invalid(val errorMessage: String): PasswordResult()
}
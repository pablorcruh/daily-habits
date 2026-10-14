package ec.com.pablorcruh.habitsapp.authentication.presentation.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val errorEmail: String? = null,
    val errorPassword: String? = null,
    val signUp: Boolean = false,
    val isLoggedIn: Boolean =false,
    val isLoading: Boolean = false
)

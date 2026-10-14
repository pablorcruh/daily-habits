package ec.com.pablorcruh.habitsapp.authentication.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel(){
    var state by mutableStateOf(LoginState())
        private set

    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.EmailChanged ->{
                state = state.copy(
                    email = event.email
                )
            }
            is LoginEvent.PasswordChanged -> {
                state = state.copy(
                    password = event.password
                )
            }
            LoginEvent.SignUp -> {
                state = state.copy(
                    signUp = true
                )
            }
            LoginEvent.Login -> {
                login()
            }
        }
    }

    private fun login(){

    }
}
package ec.com.pablorcruh.habitsapp.authentication.presentation.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(

) : ViewModel(){
    var state by mutableStateOf(SignupState())
        private set
    fun onEvent(event: SignupEvent){
        when(event){
            is SignupEvent.EmailChanged -> {
                state = state.copy(
                    email = event.email
                )
            }
            is SignupEvent.PasswordChanged ->{
                state = state.copy(
                    password = event.password
                )
            }
            SignupEvent.SignUp ->{
                    signUp()
            }
            SignupEvent.SignIn ->{
                state = state.copy(
                   logIn = true
                )
            }
        }
    }

    private fun signUp(){

    }
}
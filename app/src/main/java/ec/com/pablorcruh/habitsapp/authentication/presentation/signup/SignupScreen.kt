package ec.com.pablorcruh.habitsapp.authentication.presentation.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ec.com.pablorcruh.habitsapp.R
import ec.com.pablorcruh.habitsapp.authentication.presentation.signup.components.SignupForm

@Composable
fun SignupScreen(
    onSignIn: () -> Unit,
    onLogIn: () -> Unit,
    viewModel: SignupViewModel = hiltViewModel()
){
    val state = viewModel.state

    LaunchedEffect(state.isSignedIn) {
        if(state.isSignedIn){
            onSignIn()
        }
    }

    LaunchedEffect(state.logIn) {
        if(state.logIn){
            onLogIn()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Image(painter = painterResource(R.drawable.signup), contentDescription = null)
        SignupForm(
            state,
            viewModel::onEvent,
            modifier = Modifier.fillMaxWidth()
        )
    }
    if(state.isLoading){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
    }
}
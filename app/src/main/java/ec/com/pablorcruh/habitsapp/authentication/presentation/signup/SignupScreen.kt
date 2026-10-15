package ec.com.pablorcruh.habitsapp.authentication.presentation.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ec.com.pablorcruh.habitsapp.R
import ec.com.pablorcruh.habitsapp.authentication.presentation.signup.components.SignupForm

@Composable
fun SignupScreen(
    viewModel: SignupViewModel = hiltViewModel()
){
    val state = viewModel.state
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
}
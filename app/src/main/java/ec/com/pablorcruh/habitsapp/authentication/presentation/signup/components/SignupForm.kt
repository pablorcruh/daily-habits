package ec.com.pablorcruh.habitsapp.authentication.presentation.signup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import ec.com.pablorcruh.habitsapp.authentication.presentation.signup.SignupEvent
import ec.com.pablorcruh.habitsapp.authentication.presentation.signup.SignupState
import ec.com.pablorcruh.habitsapp.core.presentation.HabitsButton
import ec.com.pablorcruh.habitsapp.core.presentation.HabitsTextField
import ec.com.pablorcruh.habitsapp.core.presentation.HabitsTitle

@Composable
fun SignupForm(
    state: SignupState,
    onEvent: (SignupEvent) -> Unit,
    modifier: Modifier = Modifier
){
    val focusManager = LocalFocusManager.current
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HabitsTitle(title = "Create Account")
        Spacer(modifier = Modifier.height(32.dp))
        HabitsTextField(
            value = state.email,
            onValueChange = {
                onEvent(SignupEvent.EmailChanged(it))
            },
            placeholder = "Email",
            contentDescription = "Enter Email",
            modifier = Modifier.fillMaxWidth().padding(bottom= 6.dp).padding(horizontal =20.dp),
            leadingIcon = Icons.Outlined.Email,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions (onAny = {
                focusManager.moveFocus(FocusDirection.Next)
            } ),
            errorMessage = state.emailError,
            isEnabled = !state.isLoading,
            backgroundColor = Color.White
        )
        HabitsTextField(
            value = state.password,
            onValueChange = {
                onEvent(SignupEvent.PasswordChanged(it))
            },
            placeholder = "Password",
            contentDescription = "Enter Password",
            modifier = Modifier.fillMaxWidth().padding(bottom= 6.dp).padding(horizontal =20.dp),
            leadingIcon = Icons.Outlined.Email,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions (onAny = {
                focusManager.moveFocus(FocusDirection.Next)
                onEvent(SignupEvent.SignUp)
            } ),
            errorMessage = state.emailError,
            isEnabled = !state.isLoading,
            backgroundColor = Color.White
        )
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        HabitsButton(
            text = "Create Account",
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            isEnabled = !state.isLoading
        ) {
            onEvent(SignupEvent.SignUp)
        }
        TextButton(onClick = {
            onEvent(SignupEvent.LogIn)
        }){
            Text(
                text = buildAnnotatedString {
                    append("Already have an account ")
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)){
                        append("Sign in")
                    }
                },
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}
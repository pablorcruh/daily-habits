package ec.com.pablorcruh.habitsapp.home.presentation.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import ec.com.pablorcruh.habitsapp.core.presentation.HabitsButton


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeAskPermission(
    permission: String,
    modifier: Modifier = Modifier
){
    val permissionState =
        rememberPermissionState(permission = permission)
    LaunchedEffect(key1 = Unit ){
        permissionState.launchPermissionRequest()
    }
    if(permissionState.status.shouldShowRationale){
        AlertDialog(
            onDismissRequest = {},
            modifier = modifier,
            confirmButton = {
                HabitsButton (text = "Accept", modifier = Modifier.fillMaxWidth()){
                    permissionState.launchPermissionRequest()
                }
            },
            title = {
                Text(text ="Permission Required")
            },
            text = {
                Text(text = "We need this permission for app to work correctly")
            }
        )
    }
}
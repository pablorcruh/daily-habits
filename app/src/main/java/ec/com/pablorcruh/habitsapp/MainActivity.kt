package ec.com.pablorcruh.habitsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ec.com.pablorcruh.habitsapp.navigation.NavigationHost
import ec.com.pablorcruh.habitsapp.navigation.NavigationRoute
import ec.com.pablorcruh.habitsapp.ui.theme.HabitsAppCourseTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    val viewModel by viewModels<MainViewModel>()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HabitsAppCourseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavigationHost(
                        navHostController = navController,
                        startDestination = getStartDestination()
                    )
                }
            }
        }
    }


    private fun getStartDestination(): NavigationRoute{
        return if(viewModel.hasSeenOnboarding){
            NavigationRoute.Login
        }else{
            NavigationRoute.Onboarding
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


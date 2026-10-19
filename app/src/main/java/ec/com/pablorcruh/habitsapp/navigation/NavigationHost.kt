package ec.com.pablorcruh.habitsapp.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ec.com.pablorcruh.habitsapp.authentication.presentation.login.LoginScreen
import ec.com.pablorcruh.habitsapp.authentication.presentation.signup.SignupScreen
import ec.com.pablorcruh.habitsapp.home.presentation.home.HomeScreen
import ec.com.pablorcruh.habitsapp.onboarding.presentation.OnboardingScreen

@Composable
fun NavigationHost(
    navHostController : NavHostController,
    startDestination: NavigationRoute
){

    NavHost(navController = navHostController, startDestination = startDestination.route){
        composable(NavigationRoute.Onboarding.route){
            OnboardingScreen(onFinish = {
                navHostController.popBackStack()
                navHostController.navigate(NavigationRoute.Login.route)

            })
        }
        composable(NavigationRoute.Login.route){
            LoginScreen(onLogin = {
                navHostController.popBackStack()
                navHostController.navigate(NavigationRoute.Home.route)
            }, onSignUp = {
                navHostController.navigate(NavigationRoute.SignUp.route)
            })
        }
        composable(NavigationRoute.SignUp.route){
            SignupScreen(onSignIn = {
                navHostController.navigate(NavigationRoute.Home.route){
                    popUpTo(navHostController.graph.id) {
                        inclusive = true
                    }
                }
            }, onLogIn = {
                navHostController.popBackStack()
            })
        }
        composable(NavigationRoute.Home.route){
            HomeScreen()
        }
    }
}
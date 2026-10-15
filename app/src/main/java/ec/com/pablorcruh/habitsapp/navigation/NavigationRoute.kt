package ec.com.pablorcruh.habitsapp.navigation

sealed class NavigationRoute(val route: String){
    object Onboarding: NavigationRoute("onboarding")
    object Login: NavigationRoute("login")

    object Home: NavigationRoute("home")

    object SignUp: NavigationRoute("signup")
}
package ec.com.pablorcruh.habitsapp.authentication.domain.matcher

interface EmailMatcher {

    fun isValid(email: String): Boolean
}
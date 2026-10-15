package ec.com.pablorcruh.habitsapp.authentication.data.matcher

import android.util.Patterns
import ec.com.pablorcruh.habitsapp.authentication.domain.matcher.EmailMatcher

class EmailMatcherImpl: EmailMatcher  {
    override fun isValid(email: String): Boolean {
       return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
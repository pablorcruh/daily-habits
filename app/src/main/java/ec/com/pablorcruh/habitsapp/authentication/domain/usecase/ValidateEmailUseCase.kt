package ec.com.pablorcruh.habitsapp.authentication.domain.usecase

import ec.com.pablorcruh.habitsapp.authentication.domain.matcher.EmailMatcher

class ValidateEmailUseCase(private val emailMatcher: EmailMatcher) {

    operator fun invoke(email: String): Boolean{
        return emailMatcher.isValid(email)
    }
}
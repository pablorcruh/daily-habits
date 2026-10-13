package ec.com.pablorcruh.habitsapp.onboarding.domain.repository

interface OnboardingRepository {

    fun hasSeenonboarding(): Boolean
    fun completeOnboarding()
}
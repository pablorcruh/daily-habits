package ec.com.pablorcruh.habitsapp.onboarding.domain.usecase

import ec.com.pablorcruh.habitsapp.onboarding.domain.repository.OnboardingRepository

class HasSeenOnboardingUseCase(
    private val repository: OnboardingRepository
) {
    operator fun invoke(): Boolean{
        return repository.hasSeenonboarding()
    }
}
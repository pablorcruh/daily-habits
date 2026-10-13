package ec.com.pablorcruh.habitsapp.onboarding.domain.usecase

import ec.com.pablorcruh.habitsapp.onboarding.domain.repository.OnboardingRepository

class CompleteOnboardingUseCase(
    private val repository: OnboardingRepository
) {

    operator fun invoke(){
        repository.completeOnboarding()
    }
}
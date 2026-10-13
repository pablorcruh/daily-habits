package ec.com.pablorcruh.habitsapp.onboarding.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ec.com.pablorcruh.habitsapp.onboarding.domain.usecase.CompleteOnboardingUseCase
import ec.com.pablorcruh.habitsapp.onboarding.domain.usecase.HasSeenOnboardingUseCase
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val hasSeenOnboardingUseCase: HasSeenOnboardingUseCase,
    private val completeOnboardingUseCase: CompleteOnboardingUseCase
): ViewModel() {

    var hasSeenOnboarding by mutableStateOf(hasSeenOnboardingUseCase())
        private set

    fun completeOnboarding(){
        completeOnboardingUseCase()
        hasSeenOnboarding = true
    }
}
package ec.com.pablorcruh.habitsapp.onboarding.data

import android.content.SharedPreferences
import ec.com.pablorcruh.habitsapp.onboarding.domain.repository.OnboardingRepository

class OnboardingRepositoryImpl(
    private val sharedPreferences: SharedPreferences
): OnboardingRepository {

    companion object{
        private val HAS_SEEN_ONBOARDING = "has_seen_onboarding"
    }

    override fun hasSeenonboarding(): Boolean {
        return sharedPreferences.getBoolean(HAS_SEEN_ONBOARDING, false)
    }

    override fun completeOnboarding() {
        sharedPreferences.edit().putBoolean(HAS_SEEN_ONBOARDING, true).apply()
    }
}
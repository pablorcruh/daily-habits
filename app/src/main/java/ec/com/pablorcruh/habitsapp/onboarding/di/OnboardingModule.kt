package ec.com.pablorcruh.habitsapp.onboarding.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ec.com.pablorcruh.habitsapp.onboarding.data.OnboardingRepositoryImpl
import ec.com.pablorcruh.habitsapp.onboarding.domain.repository.OnboardingRepository
import ec.com.pablorcruh.habitsapp.onboarding.domain.usecase.CompleteOnboardingUseCase
import ec.com.pablorcruh.habitsapp.onboarding.domain.usecase.HasSeenOnboardingUseCase
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object OnboardingModule {

    @Provides
    @Singleton
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("habits_onboarding_preferences", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providesOnboardingRepository(sharedPreferences: SharedPreferences): OnboardingRepository {
        return OnboardingRepositoryImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun providesHasSeenOnboardingUseCase(repository: OnboardingRepository): HasSeenOnboardingUseCase {
        return HasSeenOnboardingUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesCompleteOnboardingUseCase(repository: OnboardingRepository): CompleteOnboardingUseCase {
        return CompleteOnboardingUseCase(repository)

    }

}
package ec.com.pablorcruh.habitsapp.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ec.com.pablorcruh.habitsapp.home.data.repository.HomeRepositoryImpl
import ec.com.pablorcruh.habitsapp.home.domain.home.useCases.CompleteHabitUseCase
import ec.com.pablorcruh.habitsapp.home.domain.home.useCases.GetHabitsForDateUseCase
import ec.com.pablorcruh.habitsapp.home.domain.home.useCases.HomeUseCases
import ec.com.pablorcruh.habitsapp.home.domain.repository.HomeRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HomeModule {

    @Provides
    @Singleton
    fun providesHomeRepository(): HomeRepository {
        return HomeRepositoryImpl()
    }


    @Provides
    @Singleton
    fun provideHomeUseCases(repository: HomeRepository): HomeUseCases {
        return HomeUseCases(
            completeHabitUseCase = CompleteHabitUseCase(repository),
            getHabitsForDateUseCase = GetHabitsForDateUseCase(repository)
        )
    }
}
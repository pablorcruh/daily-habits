package ec.com.pablorcruh.habitsapp.authentication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ec.com.pablorcruh.habitsapp.authentication.data.repository.AuthenticationRepositoryImpl
import ec.com.pablorcruh.habitsapp.authentication.domain.repository.AuthenticationRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    @Provides
    @Singleton
    fun providesAuthenticationRepository(): AuthenticationRepository{
        return AuthenticationRepositoryImpl()
    }
}
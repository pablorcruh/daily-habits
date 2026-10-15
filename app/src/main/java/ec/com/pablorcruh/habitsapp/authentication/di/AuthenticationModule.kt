package ec.com.pablorcruh.habitsapp.authentication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ec.com.pablorcruh.habitsapp.authentication.data.matcher.EmailMatcherImpl
import ec.com.pablorcruh.habitsapp.authentication.data.repository.AuthenticationRepositoryImpl
import ec.com.pablorcruh.habitsapp.authentication.domain.matcher.EmailMatcher
import ec.com.pablorcruh.habitsapp.authentication.domain.repository.AuthenticationRepository
import ec.com.pablorcruh.habitsapp.authentication.domain.usecase.LoginUseCases
import ec.com.pablorcruh.habitsapp.authentication.domain.usecase.LoginWithEmailUseCase
import ec.com.pablorcruh.habitsapp.authentication.domain.usecase.ValidateEmailUseCase
import ec.com.pablorcruh.habitsapp.authentication.domain.usecase.ValidatePasswordUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    @Provides
    @Singleton
    fun providesAuthenticationRepository(): AuthenticationRepository{
        return AuthenticationRepositoryImpl()
    }

    @Provides
    @Singleton
    fun providesEmailMatcher(): EmailMatcher{
        return EmailMatcherImpl()
    }



    @Provides
    @Singleton
    fun providesLoginUseCases(repository: AuthenticationRepository, matcher: EmailMatcher): LoginUseCases {
       return LoginUseCases(
           validateEmailUseCase = ValidateEmailUseCase(matcher),
           validatePasswordUseCase = ValidatePasswordUseCase(),
           loginWithEmailUseCase = LoginWithEmailUseCase(repository)
       )
   }
}
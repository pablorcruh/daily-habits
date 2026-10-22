package ec.com.pablorcruh.habitsapp.home.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ec.com.pablorcruh.habitsapp.home.data.local.HomeDao
import ec.com.pablorcruh.habitsapp.home.data.local.HomeDatabase
import ec.com.pablorcruh.habitsapp.home.data.local.typeconverter.HomeTypeConverters
import ec.com.pablorcruh.habitsapp.home.data.repository.HomeRepositoryImpl
import ec.com.pablorcruh.habitsapp.home.domain.detail.useCases.DetailUseCases
import ec.com.pablorcruh.habitsapp.home.domain.detail.useCases.GetHabitByIdUseCase
import ec.com.pablorcruh.habitsapp.home.domain.detail.useCases.InsertHabitUseCase
import ec.com.pablorcruh.habitsapp.home.domain.home.useCases.CompleteHabitUseCase
import ec.com.pablorcruh.habitsapp.home.domain.home.useCases.GetHabitsForDateUseCase
import ec.com.pablorcruh.habitsapp.home.domain.home.useCases.HomeUseCases
import ec.com.pablorcruh.habitsapp.home.domain.repository.HomeRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HomeModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi{
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun providesHabitDao(@ApplicationContext context: Context, moshi:Moshi): HomeDao{
        return Room.databaseBuilder(
            context,
            HomeDatabase::class.java,
            "habits_db"
        ).addTypeConverter(moshi).build().dao
    }

    @Provides
    @Singleton
    fun providesHomeRepository(dao: HomeDao): HomeRepository {
        return HomeRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideHomeUseCases(repository: HomeRepository): HomeUseCases {
        return HomeUseCases(
            completeHabitUseCase = CompleteHabitUseCase(repository),
            getHabitsForDateUseCase = GetHabitsForDateUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideDetailUseCases(repository: HomeRepository): DetailUseCases {
        return DetailUseCases(
            getHabitByIdUseCases = GetHabitByIdUseCase(repository),
            insertHabitUseCase = InsertHabitUseCase(repository)
        )
    }
}
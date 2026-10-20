package ec.com.pablorcruh.habitsapp.home.presentation.home

import ec.com.pablorcruh.habitsapp.home.domain.models.Habit
import java.time.ZonedDateTime

sealed interface HomeEvent {
    data class ChangeDate(val date: ZonedDateTime): HomeEvent
    data class CompleteHabit(val habit: Habit): HomeEvent
}
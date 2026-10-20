package ec.com.pablorcruh.habitsapp.home.presentation.home

import ec.com.pablorcruh.habitsapp.home.domain.models.Habit
import java.time.ZonedDateTime

data class HomeState(
    val currentDate: ZonedDateTime = ZonedDateTime.now(),
    val selectedDate: ZonedDateTime = ZonedDateTime.now(),
    val habits: List<Habit> = emptyList()
)

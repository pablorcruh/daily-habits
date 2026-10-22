package ec.com.pablorcruh.habitsapp.home.data.mapper

import ec.com.pablorcruh.habitsapp.home.data.extension.toStartOfDateTimestamp
import ec.com.pablorcruh.habitsapp.home.data.extension.toTimeStamp
import ec.com.pablorcruh.habitsapp.home.data.extension.toZonedDateTime
import ec.com.pablorcruh.habitsapp.home.data.local.entity.HabitEntity
import ec.com.pablorcruh.habitsapp.home.domain.models.Habit
import java.time.DayOfWeek

fun HabitEntity.toDomain(): Habit {
    return Habit(
        id = this.id,
        name = this.name,
        frequency = this.frequency.map{
            DayOfWeek.of(it)
        },
        completedDates = this.completeDays.map{it.toZonedDateTime().toLocalDate()},
        reminder = this.reminder.toZonedDateTime().toLocalTime(),
        startDate = this.startDate.toZonedDateTime()
    )
}

fun Habit.toEntity(): HabitEntity{
    return HabitEntity(
        id = this.id,
        name = this.name,
        frequency = this.frequency.map{it.value},
        completeDays = this.completedDates.map{it.toZonedDateTime().toTimeStamp()},
        reminder = this.reminder.toZonedDateTime().toTimeStamp(),
        startDate = this.startDate.toStartOfDateTimestamp()
    )
}
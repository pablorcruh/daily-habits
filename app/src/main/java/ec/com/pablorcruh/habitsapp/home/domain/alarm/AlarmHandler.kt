package ec.com.pablorcruh.habitsapp.home.domain.alarm

import ec.com.pablorcruh.habitsapp.home.domain.models.Habit

interface AlarmHandler {

    fun setRecurringAlarm(habit: Habit)
    fun cancel(habit: Habit)
}
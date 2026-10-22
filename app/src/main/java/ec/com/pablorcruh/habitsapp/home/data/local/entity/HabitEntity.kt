package ec.com.pablorcruh.habitsapp.home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class HabitEntity(
    @PrimaryKey(autoGenerate = false)
    val id:String,
    val name: String,
    val frequency: List<Int>,
    val completeDays: List<Long>,
    val reminder: Long,
    val startDate: Long
)

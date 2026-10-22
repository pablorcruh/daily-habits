package ec.com.pablorcruh.habitsapp.home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ec.com.pablorcruh.habitsapp.home.data.local.entity.HabitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: HabitEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabits(habits: List<HabitEntity>)

    @Query("SELECT * FROM habits WHERE id = :id")
    fun getHabitById(id: String): HabitEntity?

    @Query("SELECT * FROM habits WHERE startDate <= :date")
    fun getAllHabitsForSelectedDate(date: Long): Flow<List<HabitEntity>>

}
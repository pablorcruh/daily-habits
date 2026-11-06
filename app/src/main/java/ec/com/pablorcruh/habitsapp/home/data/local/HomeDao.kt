package ec.com.pablorcruh.habitsapp.home.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ec.com.pablorcruh.habitsapp.home.data.local.entity.HabitEntity
import ec.com.pablorcruh.habitsapp.home.data.local.entity.HabitSyncEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: HabitEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabits(habits: List<HabitEntity>)

    @Query("SELECT * FROM habits WHERE id = :id")
    suspend fun getHabitById(id: String): HabitEntity

    @Query("SELECT * FROM habits WHERE startDate <= :date ORDER BY id ASC")
    fun getAllHabitsForSelectedDate(date: Long): Flow<List<HabitEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabitSync(habitSyncEntity: HabitSyncEntity)

    @Query("SELECT * FROM HabitSyncEntity")
    fun getAllHabitsSync(): List<HabitSyncEntity>

    @Delete
    suspend fun deleteHabitSync(habitSyncEntity: HabitSyncEntity)

    @Query("SELECT * FROM habits")
    fun getAllHabits(): List<HabitEntity>

}
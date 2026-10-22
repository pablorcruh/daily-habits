package ec.com.pablorcruh.habitsapp.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ec.com.pablorcruh.habitsapp.home.data.local.entity.HabitEntity
import ec.com.pablorcruh.habitsapp.home.data.local.typeconverter.HomeTypeConverters

@Database(entities = [HabitEntity::class], version = 1, exportSchema = false)
@TypeConverters(HomeTypeConverters::class)
abstract class HomeDatabase: RoomDatabase() {
    abstract val dao: HomeDao
}
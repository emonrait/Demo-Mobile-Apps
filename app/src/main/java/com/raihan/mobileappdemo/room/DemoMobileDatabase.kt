package com.raihan.mobileappdemo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [MovieRoomModel::class],
    version = 3,
    exportSchema = false
)
abstract class DemoMobileDatabase : RoomDatabase() {
    abstract fun roomDao(): RoomDao

    companion object {
        @Volatile
        private var INSTANCE: DemoMobileDatabase? = null

        fun getDatabase(context: Context): DemoMobileDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                // var instance = Room.databaseBuilder()
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DemoMobileDatabase::class.java,
                    "demoappdb"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
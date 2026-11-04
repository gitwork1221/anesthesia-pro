package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [BookmarkEntity::class, UserNoteEntity::class, UserPreferenceEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AnesthesiaDatabase : RoomDatabase() {
    abstract fun anesthesiaDao(): AnesthesiaDao

    companion object {
        @Volatile
        private var INSTANCE: AnesthesiaDatabase? = null

        fun getDatabase(context: Context): AnesthesiaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnesthesiaDatabase::class.java,
                    "anesthesia_pro_database"
                )
                    .fallbackToDestructiveMigration(dropAllTables = true)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

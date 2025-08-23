package com.antonioladeia.cadernetamaconica.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [SessionEntity::class], version = 1, exportSchema = false)
abstract class CadernetaDatabase : RoomDatabase() {

    abstract fun sessionDao(): SessionDao

    companion object {
        @Volatile
        private var Instance: CadernetaDatabase? = null

        fun getDatabase(context: Context): CadernetaDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, CadernetaDatabase::class.java, "caderneta_database")
                    .fallbackToDestructiveMigration(false)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
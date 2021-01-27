package com.jyotirmayg.mvvmsample.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jyotirmayg.mvvmsample.data.db.dao.QuoteDao
import com.jyotirmayg.mvvmsample.data.db.dao.UserDao
import com.jyotirmayg.mvvmsample.data.db.entities.Quote
import com.jyotirmayg.mvvmsample.data.db.entities.User

/**
 * App database is the access point to db. Its a singleton class.
 */

@Database(
    entities = [User::class, Quote::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getQuotesDao(): QuoteDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MyDatabase.db"
            ).build()
    }
}
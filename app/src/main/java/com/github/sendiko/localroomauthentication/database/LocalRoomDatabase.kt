package com.github.sendiko.localroomauthentication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.sendiko.localroomauthentication.database.entities.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class LocalRoomDatabase : RoomDatabase() {

    abstract val userDao: UserDao


    companion object {

        @Volatile
        private var INSTANCE: LocalRoomDatabase? = null

        fun getInstance(context: Context): LocalRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LocalRoomDatabase::class.java,
                        "catatan.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }

}
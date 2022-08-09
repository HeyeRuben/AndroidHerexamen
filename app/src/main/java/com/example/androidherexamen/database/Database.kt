package com.example.androidherexamen.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Post::class, Comment::class, User::class], version = 1,  exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract val postDatabaseDAO: PostDatabaseDAO
    abstract val commentDatabaseDAO: CommentDatabaseDAO
    abstract val userDatabaseDAO: UserDatabaseDAO

    companion object {

        @Volatile
        private var INSTANCE: com.example.androidherexamen.database.Database? = null

        fun getInstance(context: Context): com.example.androidherexamen.database.Database {

            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        com.example.androidherexamen.database.Database::class.java,
                        "database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance

            }

        }
    }

}
package com.example.androidherexamen.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Post::class, Comment::class, User::class], version = 1,  exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val postDatabaseDAO: PostDatabaseDAO
    abstract val commentDatabaseDAO: CommentDatabaseDAO
    abstract val userDatabaseDAO: UserDatabaseDAO

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {

            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "AppDatabase"
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
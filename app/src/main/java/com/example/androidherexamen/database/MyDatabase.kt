package com.example.androidherexamen.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Post::class, Comment::class, User::class],
    version = 8,
    exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {

    abstract val postDatabaseDAO: PostDatabaseDAO
    abstract val commentDatabaseDAO: CommentDatabaseDAO
    abstract val userDatabaseDAO: UserDatabaseDAO

    companion object {

        @Volatile
        private var INSTANCE: com.example.androidherexamen.database.MyDatabase? = null

        fun getInstance(context: Context): com.example.androidherexamen.database.MyDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "my_database"
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
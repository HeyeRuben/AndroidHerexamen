package com.example.androidherexamen.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.androidherexamen.converters.Converters

@Database(
    entities = [Post::class, Comment::class, User::class],
    version = 11,
    exportSchema = false
)
@TypeConverters(Converters::class)
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
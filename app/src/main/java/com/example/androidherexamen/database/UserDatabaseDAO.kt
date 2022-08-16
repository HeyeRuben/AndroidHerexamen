package com.example.androidherexamen.database

import androidx.room.*

@Dao
interface UserDatabaseDAO {

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}
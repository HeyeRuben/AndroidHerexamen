package com.example.androidherexamen.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(

    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0L,

    var role: String = "", // nog veranderen naar role enum

    var avatar: String = "",

    var profielfoto: String = "",

    var begeleiderId: Long = 0L

)
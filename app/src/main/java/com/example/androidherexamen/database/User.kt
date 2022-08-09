package com.example.androidherexamen.database

data class User (

    var userId: Long = 0L,

    var role: String = "", // nog veranderen naar role enum

    var avatar: String = "",

    var profielfoto: String = "",

    var begeleiderId: Long = 0L

)
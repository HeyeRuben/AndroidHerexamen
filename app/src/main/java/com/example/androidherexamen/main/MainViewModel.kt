package com.example.androidherexamen.main

import android.app.Application
import androidx.lifecycle.*
import com.example.androidherexamen.database.Post
import com.example.androidherexamen.database.PostDatabaseDAO
import kotlinx.coroutines.launch

class MainViewModel(userId: Int, val database: PostDatabaseDAO, application: Application) : AndroidViewModel(application){

    // Bevat de lijst met posts
    val posts = database.getAll() // Impl: via string formatter de data weergeven

    fun updateList(){
        viewModelScope.launch{
            val newPost = Post()
            //newPost.text = kotlin.random.Random.nextInt().toString()
            insert(newPost)
        }
    }

    private suspend fun insert(newPost: Post) {
        database.insert(newPost)
    }
}
package com.example.androidherexamen.createPost

import android.app.Application
import androidx.lifecycle.*
import com.example.androidherexamen.database.Comment
import com.example.androidherexamen.database.CommentDatabaseDAO
import com.example.androidherexamen.database.Post
import com.example.androidherexamen.database.PostDatabaseDAO
import kotlinx.coroutines.launch

class CreatePostViewModel(val database: PostDatabaseDAO, application: Application) : AndroidViewModel(application){

    val newPostText = MutableLiveData("")
    val newPostLinks = MutableLiveData("")

    private suspend fun insert(newPost: Post) {
        database.insert(newPost)
    }

    fun onSavePostClicked(){
        viewModelScope.launch {
            val newPost = Post()
            newPost.text = newPostText.value!!.toString()
            newPost.links = newPostLinks.value!!.toString()
            insert(newPost)
        }
    }
}
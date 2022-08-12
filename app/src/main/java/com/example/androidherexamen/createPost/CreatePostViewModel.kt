package com.example.androidherexamen.createPost

import android.app.Application
import androidx.lifecycle.*
import com.example.androidherexamen.database.Comment
import com.example.androidherexamen.database.CommentDatabaseDAO
import com.example.androidherexamen.database.Post
import com.example.androidherexamen.database.PostDatabaseDAO
import kotlinx.coroutines.launch

class CreatePostViewModel(val database: PostDatabaseDAO, application: Application) : AndroidViewModel(application){

    private val _saveEvent = MutableLiveData<Boolean>()
    val saveEvent: LiveData<Boolean>
        get() = _saveEvent

    init {
        _saveEvent.value = false
    }

    fun saveEventDone(){
        _saveEvent.value = false
    }

    private suspend fun insert(newPost: Post) {
        database.insert(newPost)
    }

    fun submitClicked(){
        _saveEvent.value = true
    }

    fun onSavePostClicked(text: String){
        viewModelScope.launch {
            val newPost = Post()
            newPost.text = text
            insert(newPost)
        }
    }
}
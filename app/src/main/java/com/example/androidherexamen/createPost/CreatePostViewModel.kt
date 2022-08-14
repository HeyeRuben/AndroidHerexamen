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
    val addNewPostResult = MutableLiveData("")

    private suspend fun insert(newPost: Post) {
        database.insert(newPost)
    }

    fun onSavePostClicked(){

        var isValidated = validateNewPost()

        if(isValidated){
            viewModelScope.launch {
                val newPost = Post()
                newPost.text = newPostText.value!!.toString()
                newPost.links = newPostLinks.value!!.toString()
                insert(newPost)
                addNewPostResult.value = "Succes."
            }
        } else {
            addNewPostResult.value = "Error: je moet een link of post tekst toevoegen."
        }


    }

    fun validateNewPost(): Boolean {

        var enteredData = 0

        if(!newPostText.value.isNullOrEmpty())
            enteredData++
        if(!newPostLinks.value.isNullOrEmpty())
            enteredData++

        return enteredData > 0
    }
}
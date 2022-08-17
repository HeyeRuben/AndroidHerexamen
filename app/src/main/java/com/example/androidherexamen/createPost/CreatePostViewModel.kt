package com.example.androidherexamen.createPost

import android.app.Application
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.*
import com.example.androidherexamen.database.Post
import com.example.androidherexamen.database.PostDatabaseDAO
import kotlinx.coroutines.launch

class CreatePostViewModel(
    val database: PostDatabaseDAO,
    application: Application
) : AndroidViewModel(application) {

    val newPostText = MutableLiveData("")
    val newPostLinks = MutableLiveData("")
    val addNewPostResult = MutableLiveData("")
    lateinit var imageBitmap: Bitmap

    private val _navigateToMain = MutableLiveData(false)
    val navigateToMain: LiveData<Boolean>
        get() = _navigateToMain

    fun onMainNavigated() {
        _navigateToMain.value = false
    }

    private suspend fun insert(newPost: Post) {
        database.insert(newPost)
    }

    fun onSavePostClicked() {

        var isValidated = validateNewPost()

        if (isValidated) {
            viewModelScope.launch {
                val newPost = Post(photo = imageBitmap)
                newPost.text = newPostText.value!!.toString()
                newPost.links = newPostLinks.value!!.toString()
                newPost.photo = imageBitmap
                insert(newPost)
                addNewPostResult.value = "Succes."
                _navigateToMain.value = true
            }
        } else {
            addNewPostResult.value = "Error: je moet een link of post tekst toevoegen."
        }
    }

    private fun validateNewPost(): Boolean {

        var enteredData = 0

        if (!newPostText.value.isNullOrEmpty())
            enteredData++
        if (!newPostLinks.value.isNullOrEmpty())
            enteredData++

        return enteredData > 0
    }
}
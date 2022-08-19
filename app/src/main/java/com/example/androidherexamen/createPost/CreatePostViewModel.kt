package com.example.androidherexamen.createPost

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.*
import com.example.androidherexamen.Roles
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
    var loggedInUserId: String = ""
    var loggedInUsername: String = ""
    var loggedInUserRole: String = ""
    var imageBitmap: Bitmap? = null

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

        if (!validateLoggedInUser()) {
            addNewPostResult.value = "Error: Je moet inloggen om een post te maken"
        } else {
            if (isValidated) {
                viewModelScope.launch {
                    val newPost = Post()
                    newPost.userId = loggedInUserId
                    newPost.username = loggedInUsername
                    newPost.text = newPostText.value!!.toString()
                    newPost.links = newPostLinks.value!!.toString()
                    newPost.photo = imageBitmap
                    insert(newPost)
                    addNewPostResult.value = "Succes."
                    _navigateToMain.value = true
                }
            } else {
                addNewPostResult.value = "Error: je moet een link, tekst of foto toevoegen."
            }
        }
    }

    private fun validateNewPost(): Boolean {

        var enteredData = 0

        if (!newPostText.value.isNullOrEmpty())
            enteredData++
        if (!newPostLinks.value.isNullOrEmpty())
            enteredData++
        if (imageBitmap != null)
            enteredData++

        return enteredData > 0
    }

    private fun validateLoggedInUser(): Boolean {
        var validated: Boolean

        validated =
            !(loggedInUserRole != Roles.JONGERE.toString() || loggedInUserId.isNullOrEmpty())


        return validated
    }
}
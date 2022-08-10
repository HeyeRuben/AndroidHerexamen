package com.example.androidherexamen.main

import android.app.Application

import androidx.lifecycle.*
import com.example.androidherexamen.database.Post
import com.example.androidherexamen.database.PostDatabaseDAO
import kotlinx.coroutines.*

class MainViewModel(userId: Int, val database: PostDatabaseDAO, application: Application) : AndroidViewModel(application){

    private val posts = database.getAll() // later aanpassen met userId parameter

    val stringPosts = posts.value.toString() // Kan ook via formatter

    fun onAddPost(){

        viewModelScope.launch {
            val newPost = Post()

            insert(newPost)
        }

    }

    private suspend fun insert(newPost: Post) {

        database.insert(newPost)
        println(stringPosts)

    }

    /*

    //Private attribute (Mutable Live Data)
    private val _list : MutableLiveData<MutableList<String>> = MutableLiveData(mutableListOf("test1", "test2", "test3"))

    //public attribute (Live Data
    val list : LiveData<MutableList<String>>
        get() = _list

    fun updateList(){
        _list.value!!.add("new item")
        var list : MutableLiveData<MutableList<String>> = _list

        _list.value = list.value
    }

     */
}
package com.example.androidherexamen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){

    // Bevat de lijst met posts (voorbeeld lijst van tekst)

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
}
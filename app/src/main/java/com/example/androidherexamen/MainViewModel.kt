package com.example.androidherexamen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){

    // Bevat de lijst met posts (voorbeeld lijst van tekst)
    val listString = mutableListOf("test1", "test2", "test3")
    var list : MutableLiveData<MutableList<String>> = MutableLiveData(listString)

    init {
        list.value?.plus("test1");
        list.value?.plus("test2");
        list.value?.plus("test3");
    }

}
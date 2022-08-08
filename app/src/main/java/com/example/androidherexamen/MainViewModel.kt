package com.example.androidherexamen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){

    // Bevat de lijst met posts (voorbeeld lijst van tekst)

    private lateinit var list: MutableLiveData<MutableList<String>>

    init {
        list.value?.add("test1");
        list.value?.add("test2");
        list.value?.add("test3");
    }

}
package com.example.androidherexamen.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidherexamen.R
import com.example.androidherexamen.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 * Use the [main.newInstance] factory method to
 * create an instance of this fragment.
 */
class main : Fragment() {

    private lateinit var viewModel : MainViewModel
    private lateinit var viewModelFactory: MainViewModelFactory

    private lateinit var binding : FragmentMainBinding

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Inflate view + instance van binding klasse
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        viewModelFactory = MainViewModelFactory(1)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        binding.mainViewModel = viewModel
        binding.setLifecycleOwner(this)

        return binding.root
    }

    // Voor architectuur te testen
    fun updateList(){
        viewModel.updateList()
    }
}
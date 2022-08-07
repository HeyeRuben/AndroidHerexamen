package com.example.androidherexamen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.androidherexamen.databinding.FragmentMainBinding
import com.example.androidherexamen.databinding.FragmentProfileBinding

class profile : Fragment() {

    private lateinit var viewModel : ProfileViewModel

    private lateinit var binding : FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate view + instance van binding klasse
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)

        return binding.root
    }
}
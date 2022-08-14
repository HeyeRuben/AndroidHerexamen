package com.example.androidherexamen.createPost

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.androidherexamen.R
import com.example.androidherexamen.database.MyDatabase
import com.example.androidherexamen.databinding.FragmentCommentsBinding
import com.example.androidherexamen.databinding.FragmentCreatePostBinding
import com.example.androidherexamen.databinding.FragmentMainBinding
import com.example.androidherexamen.main.DeletePostListener

class CreatePostFragment : Fragment() {

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentCreatePostBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_post, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = MyDatabase.getInstance(application).postDatabaseDAO

        val viewModelFactory = CreatePostViewModelFactory(dataSource, application)

        val createPostViewModel = ViewModelProvider(this, viewModelFactory).get(CreatePostViewModel::class.java)

        binding.viewModel = createPostViewModel

        createPostViewModel.navigateToMain.observe(this, Observer {
            if(it == true){
                this.findNavController().navigate(CreatePostFragmentDirections.actionCreatePostFragmentToMain())
                createPostViewModel.onMainNavigated()
            }
        })

        binding.lifecycleOwner = this

        return binding.root
    }
}
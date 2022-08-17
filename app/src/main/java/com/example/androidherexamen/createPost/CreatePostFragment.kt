package com.example.androidherexamen.createPost

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.MediaStore
import android.provider.MediaStore.ACTION_IMAGE_CAPTURE
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
import com.example.androidherexamen.databinding.FragmentCreatePostBinding

class CreatePostFragment : Fragment() {

    lateinit var viewModelRef: CreatePostViewModel
    lateinit var bindingRef: FragmentCreatePostBinding

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentCreatePostBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_create_post,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = MyDatabase.getInstance(application).postDatabaseDAO

        val viewModelFactory = CreatePostViewModelFactory(dataSource, application)

        val createPostViewModel = ViewModelProvider(this, viewModelFactory).get(CreatePostViewModel::class.java)

        viewModelRef = createPostViewModel
        bindingRef = binding

        binding.viewModel = createPostViewModel

        binding.openFiles.setOnClickListener {
            pickImageFromGallery()
        }

        binding.openCameraButton.setOnClickListener {
            openCamera()
        }

        binding.imageView

        createPostViewModel.navigateToMain.observe(this, Observer {
            if (it == true) {
                this.findNavController().navigate(CreatePostFragmentDirections.actionCreatePostFragmentToMain())
                createPostViewModel.onMainNavigated()
            }
        })

        val sp: SharedPreferences = requireActivity().getSharedPreferences("LoggedInUser",
            Context.MODE_PRIVATE
        )

        val userId = sp.getString("id", null)

        if (userId != null) {
            createPostViewModel.loggedInUserId = userId
        }

        binding.lifecycleOwner = this

        return binding.root
    }

    private fun pickImageFromGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 100)
    }

    private fun openCamera(){
        val intent = Intent(ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

            bindingRef.imageView.setImageURI(data?.data)
            viewModelRef.imageBitmap = (bindingRef.imageView.drawable as BitmapDrawable).bitmap
    }

}
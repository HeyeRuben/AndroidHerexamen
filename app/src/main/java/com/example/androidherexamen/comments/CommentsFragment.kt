package com.example.androidherexamen.comments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidherexamen.R
import com.example.androidherexamen.database.MyDatabase
import com.example.androidherexamen.databinding.FragmentCommentsBinding
import com.example.androidherexamen.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 * Use the [CommentsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommentsFragment : Fragment() {

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentCommentsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_comments, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = MyDatabase.getInstance(application).commentDatabaseDAO

        val viewModelFactory = CommentsViewModelFactory(1, dataSource, application)

        val commentsViewModel = ViewModelProvider(this, viewModelFactory).get(CommentsViewModel::class.java)

        binding.commentsViewModel = commentsViewModel


        val adapter = CommentsAdapter()
        binding.commentsList.adapter = adapter


        commentsViewModel.comments.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = this

        return binding.root
    }
}
package com.example.androidherexamen.main

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
import com.example.androidherexamen.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = MyDatabase.getInstance(application).postDatabaseDAO

        val viewModelFactory = MainViewModelFactory(1, dataSource, application)

        val mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        binding.mainViewModel = mainViewModel

        val adapter = PostAdapter(PostCommentsListener {
            postId -> mainViewModel.onCommentsClicked(postId)
        }, DeletePostListener {
            postId -> mainViewModel.onDeletePostClicked(postId)
        }, AddPostToFavoritesClickListener {
            postId -> mainViewModel.onFavoritePostClicked(postId)
        })

        binding.postsList.adapter = adapter

        mainViewModel.navigateToComments.observe(this, Observer {post ->
            post?.let {
                this.findNavController().navigate(MainFragmentDirections
                    .actionMainToCommentsFragment(post))
                mainViewModel.onCommentsNavigated()
            }
        })

        binding.testButton.setOnClickListener{
            this.findNavController().navigate(MainFragmentDirections.actionMainToCreatePostFragment())
        }

        mainViewModel.posts.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = this

        return binding.root
    }
}
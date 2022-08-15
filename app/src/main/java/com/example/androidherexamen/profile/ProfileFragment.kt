package com.example.androidherexamen.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.androidherexamen.R
import com.example.androidherexamen.database.MyDatabase
import com.example.androidherexamen.databinding.FragmentMainBinding
import com.example.androidherexamen.databinding.FragmentProfileBinding
import com.example.androidherexamen.main.*

class ProfileFragment : Fragment() {

    private lateinit var viewModel : ProfileViewModel

    private lateinit var binding : FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentProfileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = MyDatabase.getInstance(application).postDatabaseDAO

        val viewModelFactory = ProfileViewModelFactory(1, dataSource, application)

        val profileViewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)

        binding.profileViewModel = profileViewModel

        val adapter = PostAdapter(PostCommentsListener {
                postId -> profileViewModel.onCommentsClicked(postId)
        }, DeletePostListener {
                postId -> profileViewModel.onDeletePostClicked(postId)
        })
        binding.postsListProfile.adapter = adapter

        profileViewModel.navigateToComments.observe(this, Observer {post ->
            post?.let {
                this.findNavController().navigate(
                    ProfileFragmentDirections
                        .actionProfileToCommentsFragment(post))
                profileViewModel.onCommentsNavigated()
            }
        })

        profileViewModel.favPosts.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = this

        return binding.root
    }
}
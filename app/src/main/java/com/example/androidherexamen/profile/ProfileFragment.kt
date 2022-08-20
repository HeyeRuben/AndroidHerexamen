package com.example.androidherexamen.profile

import android.content.Context
import android.content.SharedPreferences
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
import com.example.androidherexamen.Roles
import com.example.androidherexamen.database.MyDatabase
import com.example.androidherexamen.databinding.FragmentProfileBinding
import com.example.androidherexamen.main.*

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentProfileBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_profile,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = MyDatabase.getInstance(application).postDatabaseDAO

        val viewModelFactory = ProfileViewModelFactory(dataSource, application)

        val profileViewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)

        binding.profileViewModel = profileViewModel

        val adapter = PostAdapter(PostCommentsListener {
                postId -> profileViewModel.onCommentsClicked(postId)
        }, DeletePostListener {
                postId -> profileViewModel.onDeletePostClicked(postId)
        }, AddPostToFavoritesClickListener {
                postId -> profileViewModel.onFavoritePostClicked(postId)
        }, AddPostToGelezenClickListener {
                postId -> profileViewModel.onGelezenPostClicked(postId)
        })

        val sp: SharedPreferences = requireActivity().getSharedPreferences("LoggedInUser",
            Context.MODE_PRIVATE
        )

        val userId = sp.getString("id", null)
        val userName = sp.getString("name", null)
        val role = sp.getString("role", null)

        profileViewModel.userId.value = userId.toString()
        profileViewModel.userName.value = userName

        binding.postsListProfile.adapter = adapter

        if (role.toString() != Roles.JONGERE.toString()) {
            binding.postsListProfile.visibility = View.INVISIBLE
            binding.wrongRole.visibility = View.VISIBLE
        } else {
            binding.postsListProfile.visibility = View.VISIBLE
            binding.wrongRole.visibility = View.INVISIBLE
        }

        profileViewModel.navigateToComments.observe(viewLifecycleOwner, Observer { post ->
            post?.let {
                this.findNavController().navigate(
                    ProfileFragmentDirections
                        .actionProfileToCommentsFragment(post))
                profileViewModel.onCommentsNavigated()
            }
        })

        profileViewModel.posts.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = this

        return binding.root
    }
}
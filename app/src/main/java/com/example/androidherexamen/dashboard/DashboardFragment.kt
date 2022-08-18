package com.example.androidherexamen.dashboard

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
import com.example.androidherexamen.database.MyDatabase
import com.example.androidherexamen.databinding.FragmentDashboardBinding
import com.example.androidherexamen.main.*

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentDashboardBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_dashboard,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = MyDatabase.getInstance(application).postDatabaseDAO

        val viewModelFactory = DashboardViewModelFactory(dataSource, application)

        val dashboardViewModel = ViewModelProvider(this, viewModelFactory).get(DashboardViewModel::class.java)

        val sp: SharedPreferences = requireActivity().getSharedPreferences("LoggedInUser",
            Context.MODE_PRIVATE
        )

        val userId = sp.getString("id", null)

        dashboardViewModel.userId.value = userId

        binding.dashboardViewModel = dashboardViewModel

        val adapter = PostAdapter(PostCommentsListener {
                postId -> dashboardViewModel.onCommentsClicked(postId)
        }, DeletePostListener {
                postId -> dashboardViewModel.onDeletePostClicked(postId)
        }, AddPostToFavoritesClickListener {
                postId -> dashboardViewModel.onFavoritePostClicked(postId)
        })

        binding.dashboardPostsList.adapter = adapter

        dashboardViewModel.navigateToComments.observe(this.viewLifecycleOwner, Observer { post ->
            post?.let {
                this.findNavController().navigate(
                    DashboardFragmentDirections
                        .actionDashboardToCommentsFragment(post))
                dashboardViewModel.onCommentsNavigated()
            }
        })

        dashboardViewModel.posts.observe(this.viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = this

        return binding.root
    }
}
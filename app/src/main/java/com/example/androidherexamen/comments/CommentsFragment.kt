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

class CommentsFragment : Fragment() {

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentCommentsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_comments,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = MyDatabase.getInstance(application).commentDatabaseDAO

        val viewModelFactory = CommentsViewModelFactory(
            CommentsFragmentArgs.fromBundle(requireArguments()).postId,
            dataSource,
            application
        )

        val commentsViewModel = ViewModelProvider(this, viewModelFactory).get(CommentsViewModel::class.java)

        binding.commentsViewModel = commentsViewModel

        val adapter = CommentsAdapter(DeleteCommentListener {
                commentId -> commentsViewModel.onDeleteCommentClicked(commentId)
        },
        ReplyCommentListener {
            commentId -> commentsViewModel.onReplyToCommentClicked(commentId)
        },
        EditCommentListener {
            commentId -> commentsViewModel.onEditCommentClicked(commentId)
        })

        binding.commentsList.adapter = adapter

        commentsViewModel.isResetButtonVisible.observe(this, Observer {
            val bttn = binding.buttonReset

            if (it == true) {
                bttn.visibility = View.VISIBLE
            } else {
                bttn.visibility = View.INVISIBLE
            }
        })

        commentsViewModel.comments.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = this

        return binding.root
    }
}
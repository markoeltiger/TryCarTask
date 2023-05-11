package com.example.trycartask.ui.screens.details

import androidx.lifecycle.ViewModel
import com.example.trycartask.data.repos.CommentsRepository
import com.example.trycartask.data.repos.PostsRepository


class DetailsViewModel(
    private val postsRepo: PostsRepository,
    private val commentsRepo: CommentsRepository
) : ViewModel() {


    fun getSinglePost(id: Int) = postsRepo.getPostById(id)

    fun getComments(id: Int) = commentsRepo.getComments(id)
    fun addBookmark(id: Int) = postsRepo.addBookmark(id)
    fun deleteBookmark(id: Int) = postsRepo.deleteBookmark(id)

}
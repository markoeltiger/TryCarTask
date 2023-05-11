package com.example.trycartask.ui.screens.home

import androidx.lifecycle.ViewModel
import com.example.trycartask.data.repos.PostsRepository


class PostsViewModel(private val postsRepo: PostsRepository) : ViewModel() {


    fun getPosts() = postsRepo.getStories()

    fun getBookMarkedPosts() = postsRepo.getBookmarkedPosts()


}
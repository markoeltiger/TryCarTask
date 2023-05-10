package com.example.trycartask.ui.screens.home

import androidx.lifecycle.ViewModel
import com.example.trycartask.data.repos.PostsRepository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class PostsViewModel(private val postsRepo: PostsRepository) : ViewModel() {


    fun getPosts() = postsRepo.getStories()



}
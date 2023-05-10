package com.example.trycartask.data.remote


import com.example.trycartask.data.models.Posts
import com.example.trycartask.utils.Constants

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET(Constants.POSTS_ENDPOINT)
    suspend fun getPosts(

    ): Response<Posts>

}

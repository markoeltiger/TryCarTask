package com.example.trycartask.data.remote


import com.example.trycartask.data.models.Comments
import com.example.trycartask.data.models.Posts
import com.example.trycartask.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET(Constants.POSTS_ENDPOINT)
    suspend fun getPosts(

    ): Response<Posts>

    @GET(Constants.POSTS_ENDPOINT + "/{postId}/comments")
    suspend fun getComments(
        @Path("postId") postId: Int,

        ): Response<Comments>
}

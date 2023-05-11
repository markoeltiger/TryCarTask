package com.example.trycartask.ui.screens.home

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.trycartask.data.models.PostsItem


@Composable
fun PostsList(
    viewModel: PostsViewModel,
    navigateToProfile: (PostsItem) -> Unit,
    favorite: Boolean
) {
    if (!favorite) {
        val posts by viewModel.getPosts().collectAsState(initial = emptyList())

        LazyColumn {

            items(items = posts,
                itemContent = { PostListItem(post = it, navigateToProfile = navigateToProfile) }
            )
            Log.e(
                "PostsList", posts.size.toString()
            )
        }
    } else {
        val favoritePosts by viewModel.getBookMarkedPosts().collectAsState(initial = emptyList())

        LazyColumn {

            items(items = favoritePosts,
                itemContent = { PostListItem(post = it, navigateToProfile = navigateToProfile) }
            )
            Log.e(
                "FavoritePostsList", favoritePosts.size.toString()
            )
        }
    }
}


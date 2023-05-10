package com.example.trycartask.ui.screens.home

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trycartask.data.models.PostsItem


@Composable
fun PostsList(viewModel:PostsViewModel,navigateToProfile: (PostsItem) -> Unit) {
    val posts by viewModel.getPosts().collectAsState(initial = emptyList())

    LazyColumn(){

        items(items = posts
            ,
            itemContent = { PostListItem(post = it, navigateToProfile = navigateToProfile) }
        )
        Log.e("PostsList",posts.size.toString()
        )
    }
}


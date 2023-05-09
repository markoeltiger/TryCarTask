package com.example.trycartask

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

import android.view.Menu
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupPositionProvider
import com.example.trycartask.data.models.Posts
import com.example.trycartask.data.models.PostsItem
import com.example.trycartask.ui.screens.home.HomeScreen
import com.example.trycartask.ui.screens.home.PostListItem
import com.example.trycartask.ui.theme.TryCarTaskTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TryCarTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    com.example.trycartask.ui.screens.home.Greeting("Android")
                 }
            }
        }
    }

    @Composable
    fun PostsList(posts: Posts,navigateToProfile: (PostsItem) -> Unit) {
       LazyColumn(){
           items(items = posts
           ,
               itemContent = { PostListItem(post = it, navigateToProfile = navigateToProfile) }
           )
       }
    }

}



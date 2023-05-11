package com.example.trycartask

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.trycartask.ui.screens.home.PostsList
import com.example.trycartask.ui.screens.home.PostsViewModel
import com.example.trycartask.ui.theme.TryCarTaskTheme
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val liveViewModel by inject<PostsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TryCarTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    TabScreen()
                 }
            }
        }
    }
    @Composable
    fun TabScreen() {
        var tabIndex by remember { mutableStateOf(0) }

        val tabs = listOf("Posts", "Favorites ")

        Column(modifier = Modifier.fillMaxWidth()) {
            TabRow(selectedTabIndex = tabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
            when (tabIndex) {
                0 -> PostsList(liveViewModel, {}, false)
                1 -> PostsList(liveViewModel, {}, true)

            }
        }
    }


}



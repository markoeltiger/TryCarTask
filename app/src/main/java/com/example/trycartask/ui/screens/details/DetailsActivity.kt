package com.example.trycartask.ui.screens.details

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.trycartask.ui.theme.TryCarTaskTheme
import org.koin.android.ext.android.inject

class DetailsActivity : ComponentActivity() {
    private val liveViewModel by inject<DetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val activity = context.findActivity()
            val intent = activity?.intent
            val id = intent!!.getIntExtra("id", 0)
            TryCarTaskTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    com.example.trycartask.ui.screens.details.DetailsComponenet(
                        viewModel = liveViewModel,
                        id,
                        navigateToProfile = {}
                    )

                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    TryCarTaskTheme {
        Greeting2("Android")
    }
}
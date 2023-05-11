package com.example.trycartask.ui.screens.details

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.trycartask.data.models.PostsItem

@Composable
fun DetailsComponenet(
    viewModel: DetailsViewModel,
    id: Int,
    navigateToProfile: (PostsItem) -> Unit
) {
    Column(
        modifier = Modifier.wrapContentHeight(), verticalArrangement = Arrangement.Top
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .wrapContentHeight(),
            elevation = 2.dp,
            backgroundColor = Color.White,
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        ) {
            val post by viewModel.getSinglePost(72).collectAsState(initial = PostsItem("", "", 0))
            Row(Modifier.clickable {


                navigateToProfile(post)
            }) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .align(Alignment.Top)
                ) {
                    Text(text = post.title!!, style = typography.h6)
                    Text(text = post.body!!, style = typography.caption)

                }
            }

        }
        Divider(
            color = Color.DarkGray,
            modifier = Modifier
                .fillMaxWidth()  //fill the max height
                .width(1.dp)
        )
        val comments by viewModel.getComments(7).collectAsState(initial = emptyList())

        LazyColumn(modifier = Modifier.padding(10.dp)) {

            items(items = comments,
                itemContent = {
                    com.example.trycartask.ui.screens.details.PostListItem(
                        comment = it,
                        navigateToProfile = navigateToProfile
                    )
                }
            )
            Log.e(
                "PostsList", comments.size.toString()
            )
        }
    }

}



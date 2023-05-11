package com.example.trycartask.ui.screens.home

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trycartask.data.models.PostsItem
import com.example.trycartask.ui.screens.details.DetailsActivity

@Composable
fun PostListItem(post: PostsItem, navigateToProfile: (PostsItem) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {
        val context = LocalContext.current

        Row(Modifier.clickable {

            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("postId", post.id)
            context.startActivity(intent)

            navigateToProfile(post)
        }) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = post.title!!, style = typography.h6)
                Text(text = post.body!!, style = typography.caption)
            }
        }
    }
}


@Preview
@Composable
fun PreviewPuppyItem() {
    val postSample = PostsItem("test","test",1)
    PostListItem(post = postSample, navigateToProfile = {})
}

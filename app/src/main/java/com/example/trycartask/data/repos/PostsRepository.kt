package com.example.trycartask.data.repos

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.example.trycartask.data.local.AppDatabase
import com.example.trycartask.data.models.Posts
import com.example.trycartask.data.models.PostsItem
import com.example.trycartask.data.remote.Api
import com.example.trycartask.utils.NetworkState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.net.ssl.SSLException
import kotlin.coroutines.CoroutineContext


class PostsRepository(
    private val context: Context,
    private val db: AppDatabase,

    private val apiList: Api,
    private val coroutineContext: CoroutineContext
) {
    /**
     * Inserts the response into the database.
     */
    private fun insertResultIntoDb(posts: Posts) {
        val dao = db.postsDao()
        CoroutineScope(this.coroutineContext).launch {

            dao.insert(posts)
        }
    }

    fun getPostById(id: Int): kotlinx.coroutines.flow.Flow<PostsItem> {
        val dao = db.postsDao()
        return dao.getPostbyId(id).asFlow()
    }

    fun getStories(): kotlinx.coroutines.flow.Flow<List<PostsItem>> {
        val dao = db.postsDao()
        val networkState = MutableLiveData<NetworkState>()
        val refreshTrigger = MutableLiveData<Unit?>()
        getResponse()
        return dao.getAllPosts().asFlow()


    }

    private fun getResponse() {
        try {
            CoroutineScope(this.coroutineContext).launch {
                val response = apiList.getPosts()
                if (!response.isSuccessful) {
                    val error = response.errorBody()
                    Log.e("Error", "Error With Response")

                }
                insertResultIntoDb(response.body()!!)
            }

        } catch (e: SSLException) {
            e.printStackTrace()
        } catch (e: UnknownHostException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}



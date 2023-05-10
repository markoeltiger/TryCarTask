package com.example.trycartask.data.repos

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.switchMap
import com.example.trycartask.R
import com.example.trycartask.data.local.AppDatabase
import com.example.trycartask.data.models.Posts
import com.example.trycartask.data.models.PostsItem
import com.example.trycartask.data.remote.Api
import com.example.trycartask.utils.LiveDataCollection
import com.example.trycartask.utils.NetworkState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import java.util.concurrent.Flow
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


    fun getStories(): kotlinx.coroutines.flow.Flow<List<PostsItem>> {
        val dao = db.postsDao()
        val networkState = MutableLiveData<NetworkState>()
        val refreshTrigger = MutableLiveData<Unit?>()
        getResponse()
        return dao.getAllPosts().asFlow()


    }

    private   fun getResponse() {
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



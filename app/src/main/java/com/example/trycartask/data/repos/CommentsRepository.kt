package com.example.trycartask.data.repos

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.example.trycartask.data.local.AppDatabase
import com.example.trycartask.data.models.Comments
import com.example.trycartask.data.models.CommentsItem
import com.example.trycartask.data.remote.Api
import com.example.trycartask.utils.NetworkState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.net.ssl.SSLException
import kotlin.coroutines.CoroutineContext


class CommentsRepository(
    private val context: Context,
    private val db: AppDatabase,

    private val apiList: Api,
    private val coroutineContext: CoroutineContext
) {
    /**
     * Inserts the response into the database.
     */
    private fun insertResultIntoDb(comments: Comments) {
        val dao = db.commentsDao()
        CoroutineScope(this.coroutineContext).launch {

            dao.insert(comments)
        }
    }


    fun getComments(postId: Int): kotlinx.coroutines.flow.Flow<List<CommentsItem>> {
        val dao = db.commentsDao()
        val networkState = MutableLiveData<NetworkState>()
        val refreshTrigger = MutableLiveData<Unit?>()
        getResponse(postId)
        return dao.getAllComments().asFlow()


    }

    private fun getResponse(postId: Int) {
        try {
            CoroutineScope(this.coroutineContext).launch {
                val response = apiList.getComments(postId)
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



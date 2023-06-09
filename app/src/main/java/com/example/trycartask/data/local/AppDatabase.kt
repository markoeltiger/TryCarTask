package com.example.trycartask.data.local


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.trycartask.data.models.CommentsItem
import com.example.trycartask.data.models.PostsItem


//basic database creation with its Dao classes
@Database(entities = [PostsItem::class, CommentsItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
    abstract fun commentsDao(): CommentsDao
}


@Dao
interface PostsDao {

    @Query("SELECT * FROM post_table ORDER BY id DESC")
    fun getAllPosts(): LiveData<List<PostsItem>>

    @Query("SELECT * FROM post_table WhERE bookmarked = 1 ORDER BY id DESC ")
    fun getBookmarkedPosts(): LiveData<List<PostsItem>>

    @Query("SELECT * FROM post_table WHERE id =:id ")
    fun getPostbyId(id: Int): LiveData<PostsItem>

    @Query("UPDATE  post_table SET bookmarked=1 WHERE id =:id ")
    fun addBookmark(id: Int)

    @Query("UPDATE  post_table SET bookmarked=0 WHERE id =:id ")
    fun deleteBookmark(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(posts: List<PostsItem>)


    @Query("DELETE FROM post_table")
    suspend fun deleteTable()

}

@Dao
interface CommentsDao {
    @Query("SELECT * FROM comment_table ORDER BY id DESC")
    fun getAllComments(): LiveData<List<CommentsItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(posts: List<CommentsItem>)

}

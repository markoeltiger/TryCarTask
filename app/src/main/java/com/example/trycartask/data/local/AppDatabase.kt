package com.example.trycartask.data.local


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.trycartask.data.models.CommentsItem
import com.example.trycartask.data.models.Posts
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
    fun getAllPosts( ): LiveData<List<PostsItem>>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(posts: List<PostsItem>)


    @Query("DELETE FROM post_table")
    suspend fun deleteTable()

}

@Dao
interface CommentsDao {

}

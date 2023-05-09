package com.example.trycartask.data.models


import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "post_table", indices = [Index(value = ["title"], unique = true)])
data class PostsItem(
    @SerializedName("body")
    val body: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("userId")
    val userId: Int?
):java.io.Serializable{
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int = 0
    var bookmarked: Boolean = false
    var entryDate = Date().time

}
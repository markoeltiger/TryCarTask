package com.example.trycartask.data.models


import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "comment_table", indices = [Index(value = ["id"], unique = true)])

data class CommentsItem(
    @SerializedName("body")
    val body: String?,
    @SerializedName("email")
    val email: String?,
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("postId")
    val postId: Int?
)
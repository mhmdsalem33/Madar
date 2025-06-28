package com.salem.madar.domain.models

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Keep
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "user_name")
    val name: String,

    @ColumnInfo(name = "user_age")
    val age: Int,

    @ColumnInfo(name = "user_job_title")
    val jobTitle: String,

    @ColumnInfo(name = "user_gender")
    val gender: String
)
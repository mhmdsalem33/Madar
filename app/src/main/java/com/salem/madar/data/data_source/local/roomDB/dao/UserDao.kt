package com.salem.madar.data.data_source.local.roomDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.salem.madar.domain.models.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun upsertUser(user: User) : Long


    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>



}
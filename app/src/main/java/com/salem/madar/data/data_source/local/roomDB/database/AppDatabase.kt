package com.salem.madar.data.data_source.local.roomDB.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.salem.madar.data.data_source.local.roomDB.dao.UserDao
import com.salem.madar.domain.models.User


@Database( entities = [User::class ] , version = 1 )
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}
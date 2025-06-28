package com.salem.madar.data.repo

import com.salem.madar.data.data_source.local.roomDB.dao.UserDao
import com.salem.madar.domain.models.User
import com.salem.madar.domain.repo.UpsertUserRepository
import javax.inject.Inject

class UpsertUserRepoImpl @Inject constructor( private val userDao: UserDao ) : UpsertUserRepository{
    override suspend fun upsertUser(user: User): Boolean {
       return userDao.upsertUser(user) > 0 // return true if user saved
    }
}
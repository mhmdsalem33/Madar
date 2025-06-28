package com.salem.madar.data.repo

import com.salem.madar.data.data_source.local.roomDB.dao.UserDao
import com.salem.madar.domain.models.User
import com.salem.madar.domain.repo.GetUsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersRepositoryImpl  @Inject constructor(
    private val userDao: UserDao
): GetUsersRepository {
    override suspend fun getUsers(): Flow<List<User>> = userDao.getAllUsers()
}
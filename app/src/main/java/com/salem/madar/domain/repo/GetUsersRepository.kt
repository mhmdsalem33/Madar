package com.salem.madar.domain.repo

import com.salem.madar.domain.models.User
import kotlinx.coroutines.flow.Flow

interface GetUsersRepository {
    suspend fun getUsers(): Flow<List<User>>
}
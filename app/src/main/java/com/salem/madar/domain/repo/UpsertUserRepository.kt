package com.salem.madar.domain.repo

import com.salem.madar.domain.models.User

interface UpsertUserRepository {
   suspend fun upsertUser( user: User  ) : Boolean
}
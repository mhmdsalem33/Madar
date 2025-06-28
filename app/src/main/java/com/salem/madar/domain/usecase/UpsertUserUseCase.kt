package com.salem.madar.domain.usecase

import com.salem.madar.domain.models.User
import com.salem.madar.domain.repo.UpsertUserRepository
import javax.inject.Inject

class UpsertUserUseCase @Inject constructor(
    private val upsertUserRepository : UpsertUserRepository
){
    suspend operator fun invoke( user : User ) : Boolean = upsertUserRepository.upsertUser(user)
}
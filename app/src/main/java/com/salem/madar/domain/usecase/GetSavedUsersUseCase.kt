package com.salem.madar.domain.usecase

import com.salem.madar.domain.repo.GetUsersRepository
import javax.inject.Inject

class GetSavedUsersUseCase @Inject constructor(
    private val getUsersRepository: GetUsersRepository
){
    suspend  operator fun invoke() = getUsersRepository.getUsers()
}
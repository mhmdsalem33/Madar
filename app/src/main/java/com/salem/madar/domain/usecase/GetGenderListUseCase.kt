package com.salem.madar.domain.usecase

import com.salem.madar.domain.repo.GenderRepository
import javax.inject.Inject

class GetGenderListUseCase @Inject constructor(
    private val genderRepository: GenderRepository
) {
    suspend operator fun invoke() =  genderRepository.getGenders()

}
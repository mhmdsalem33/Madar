package com.salem.madar.domain.repo

import com.salem.madar.domain.models.GenderModel

interface GenderRepository {
    suspend fun getGenders(): List<GenderModel>
}
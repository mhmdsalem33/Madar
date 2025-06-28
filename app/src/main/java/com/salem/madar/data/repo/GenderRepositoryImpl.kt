package com.salem.madar.data.repo

import android.content.Context
import com.salem.madar.data.data_source.raws.loadGendersFromAssets
import com.salem.madar.domain.models.GenderModel
import com.salem.madar.domain.repo.GenderRepository
import javax.inject.Inject


class GenderRepositoryImpl @Inject constructor(
     private val context: Context
) : GenderRepository {
    override suspend fun getGenders(): List<GenderModel> =  loadGendersFromAssets(context)
}
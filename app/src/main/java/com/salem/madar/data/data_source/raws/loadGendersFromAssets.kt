package com.salem.madar.data.data_source.raws

import android.content.Context
import com.salem.madar.domain.models.GenderModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json



suspend fun loadGendersFromAssets(context: Context): List<GenderModel> = withContext(Dispatchers.IO) {
    val jsonString = context.assets.open("gender_list.json").bufferedReader().use { it.readText() }
    Json.decodeFromString(jsonString)
}

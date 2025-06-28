package com.salem.madar.domain.models

import kotlinx.serialization.Serializable


@Serializable
data class GenderModel (
    val id : Int,
    val genderType : String,
    val emoji : String
)
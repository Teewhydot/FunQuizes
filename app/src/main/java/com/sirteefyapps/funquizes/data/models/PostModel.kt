package com.sirteefyapps.funquizes.data.models

import kotlinx.serialization.Serializable


@Serializable
data class PostModel (
    val body:String,
    val id:Int,
    val title:String,
    val userId:Int
    )

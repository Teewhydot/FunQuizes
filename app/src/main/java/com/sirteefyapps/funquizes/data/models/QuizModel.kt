package com.sirteefyapps.funquizes.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuizModel(
    @SerialName("response_code")
    val responseCode: Int = 0,
    @SerialName("results")
    val results: List<Result> = listOf()
)

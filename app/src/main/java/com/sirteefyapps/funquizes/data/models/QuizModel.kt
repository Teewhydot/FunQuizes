package com.sirteefyapps.funquizes.data.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class QuizModel(
    @SerialName("response_code")
    val responseCode: Int = 0,
    @SerialName("results")
    val results: List<Result> = listOf()
): Parcelable

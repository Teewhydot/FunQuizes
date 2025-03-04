package com.sirteefyapps.funquizes.data.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Parcelize
@Serializable
data class Result(
    @SerialName("type")
    val type: String = "",
    @SerialName("difficulty")
    val difficulty: String = "",
    @SerialName("category")
    val category: String = "",
    @SerialName("question")
    val question: String = "",
    @SerialName("correct_answer")
    val correctAnswer: String = "",
    @SerialName("incorrect_answers")
    val incorrectAnswers: List<String> = listOf()
): Parcelable

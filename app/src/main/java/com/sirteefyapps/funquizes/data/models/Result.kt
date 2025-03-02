package com.sirteefyapps.funquizes.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
)

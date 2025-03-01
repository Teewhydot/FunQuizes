package com.sirteefyapps.funquizes.data.models

data class FunQuizQuestions(
    val responseCode: Int = 0, // 0
    val questionList: List<Question> = listOf()
)

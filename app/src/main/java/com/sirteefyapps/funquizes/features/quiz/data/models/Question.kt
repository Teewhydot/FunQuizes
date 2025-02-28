package com.sirteefyapps.funquizes.features.quiz.data.models

data class Question(
    val category: String = "", // Entertainment: Film
    val correctAnswer: String = "", // Plan 9 from Outer Space
    val difficulty: String = "", // hard
    val incorrectAnswers: List<String> = listOf(),
    val question: String = "", // Which sci-fi cult films plot concerns aliens attempting to prevent humans from creating a doomsday weapon?
    val type: String = "" // multiple
)

package com.sirteefyapps.funquizes.domain.repository

import com.sirteefyapps.funquizes.data.models.FunQuizQuestions
import retrofit2.http.GET
import retrofit2.http.Query

interface FunQuizApi {
@GET("api.php")
suspend fun getFunQuizQuestions(
    @Query("category") category: String,
    @Query("difficulty") difficulty: String,
    @Query("type") type: String
): FunQuizQuestions
}

package com.sirteefyapps.funquizes.data.data_source

import com.sirteefyapps.funquizes.data.models.FunQuizQuestions
import com.sirteefyapps.funquizes.domain.DataOrException
import com.sirteefyapps.funquizes.domain.repository.FunQuizApi
import javax.inject.Inject

class FunQuizDataSource @Inject constructor(private  val funQuizApi: FunQuizApi) {
    private val listOfFunQuizItems = DataOrException<FunQuizQuestions, Boolean, Exception>(isLoading = false)
    suspend fun getFunQuizQuestions(type:String, category:String, difficulty:String): DataOrException<FunQuizQuestions,Boolean,Exception> {
        try {
            listOfFunQuizItems.isLoading = true
            val response = funQuizApi.getFunQuizQuestions(
                    type = type,
                    category = category,
                    difficulty = difficulty
            )
            listOfFunQuizItems.data = response
            if(listOfFunQuizItems.data?.toString()?.isNotEmpty() == true){
                listOfFunQuizItems.isLoading = false
            }
        } catch (e: Exception) {
            listOfFunQuizItems.exception = e
        }
        listOfFunQuizItems.isLoading = false
        return listOfFunQuizItems
    }
}

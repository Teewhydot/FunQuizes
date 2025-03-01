package com.sirteefyapps.funquizes.features.quiz.presentation.manager

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sirteefyapps.funquizes.data.data_source.FunQuizDataSource
import com.sirteefyapps.funquizes.data.models.FunQuizQuestions
import com.sirteefyapps.funquizes.domain.DataOrException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FunQuizViewModel @Inject constructor(private val funQuizDataSource: FunQuizDataSource):ViewModel(){
    private val _questionList: MutableState<DataOrException<FunQuizQuestions, Boolean, Exception>> =
        mutableStateOf(
            DataOrException(null, false, Exception("No data"))
        )
    val questionList = _questionList

    suspend fun getQuestions(type:String,category:String,difficulty:String) {
        _questionList.value = funQuizDataSource.getFunQuizQuestions(type,category,difficulty)
    }

}

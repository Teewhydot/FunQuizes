package com.sirteefyapps.funquizes.features.quiz.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sirteefyapps.funquizes.data.models.QuizModel
import com.sirteefyapps.funquizes.features.quiz.presentation.widgets.CustomButton
import com.sirteefyapps.funquizes.ui.theme.AppColors
import com.sirteefyapps.funquizes.ui.theme.Typography

@Composable
fun QuizScreen(quizModelFromConfigure: QuizModel,navController: NavController) {
    val currentQuestionIndex = remember { mutableIntStateOf(0) }
    val selectedOption = remember { mutableStateOf(false) }
    var currentQuestion = remember {
        quizModelFromConfigure.results[currentQuestionIndex.intValue]
    }
    val questionOptions = remember {
       if(currentQuestion.type == "boolean") mutableListOf(
              "True",
              "False"
         ) else mutableListOf(
              currentQuestion.correctAnswer,
              currentQuestion.incorrectAnswers[0],
              currentQuestion.incorrectAnswers[1],
              currentQuestion.incorrectAnswers[2]
       )
    }
    Surface(modifier = Modifier.fillMaxSize(), color = AppColors.darkPurple) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = AppColors.white,
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )
                Spacer(
                    modifier = Modifier.width(10.dp)
                )
                Text(
                    text = "Configure Quiz",
                    style = Typography.headlineSmall,
                    color = AppColors.white,
                )
            }
           Column(modifier = Modifier.padding(16.dp).fillMaxWidth().fillMaxHeight(
                0.3f
           )) {
               Spacer(
                   modifier = Modifier.height(40.dp)
               )
               Text(
                   text = quizModelFromConfigure.results[currentQuestionIndex.intValue].question,
                   color = AppColors.white

               )
           }
          Column {
                Spacer(
                    modifier = Modifier.height(20.dp)
                )
                questionOptions.shuffled().forEach {
                    OptionComposable(
                        selected = selectedOption.value,
                        onClick = {},
                        text = it,
                        buttonColor = AppColors.lightPurple
                    )
                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )
                }
          }
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            CustomButton(
               buttonColor = AppColors.brown,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    currentQuestionIndex.intValue++
                    currentQuestion = quizModelFromConfigure.results[currentQuestionIndex.intValue]
                },
                text = "Check Answer"
            )
        }
    }

}




@Composable
private fun OptionComposable(selected: Boolean = false,buttonColor:Color, onClick: () -> Unit = {}, text: String) {
 Surface(modifier = Modifier.fillMaxWidth().height(50.dp), color = buttonColor, shape = RoundedCornerShape(20.dp)) {
     Row(
         horizontalArrangement = Arrangement.Start,
         verticalAlignment = Alignment.CenterVertically,
         modifier = Modifier.clickable { onClick() }
     ) {
         RadioButton(
             selected = selected,
             onClick = {
                    onClick()
             },
             modifier = Modifier.padding(16.dp)
         )
         Spacer(
             modifier = Modifier.width(10.dp)
         )
         Text(
             text = text,
             color = AppColors.white
         )
     }
 }
}

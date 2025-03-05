package com.sirteefyapps.funquizes.features.quiz.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sirteefyapps.funquizes.data.models.QuizModel
import com.sirteefyapps.funquizes.features.quiz.presentation.widgets.CustomButton
import com.sirteefyapps.funquizes.ui.theme.AppColors
import com.sirteefyapps.funquizes.ui.theme.Typography

@Composable
fun QuizScreen(quizModelFromConfigure: QuizModel, navController: NavController) {
    val currentQuestionIndex = remember { mutableIntStateOf(0) }
    val userChecksAnswer = remember { mutableStateOf(false) }
    val userHasChosen = remember { mutableStateOf(false) }
    val selectedOptionIndex = remember { mutableIntStateOf(-1) } // -1 means no option selected
    val context = LocalContext.current
    var scoreCount by remember { mutableIntStateOf(0) }

    val currentQuestion = remember (currentQuestionIndex.intValue){
        quizModelFromConfigure.results[currentQuestionIndex.intValue]
    }
    val questionOptions = remember(currentQuestionIndex.intValue) {
        if (currentQuestion.type == "boolean") listOf(
            "True",
            "False"
        ).shuffled() else listOf(
            quizModelFromConfigure.results[currentQuestionIndex.intValue].correctAnswer,
            quizModelFromConfigure.results[currentQuestionIndex.intValue].incorrectAnswers[0],
            quizModelFromConfigure.results[currentQuestionIndex.intValue].incorrectAnswers[1],
            quizModelFromConfigure.results[currentQuestionIndex.intValue].incorrectAnswers[2],
        ).shuffled()
    }
    Surface(modifier = Modifier.fillMaxSize(), color = AppColors.darkPurple) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .windowInsetsPadding(WindowInsets.safeContent)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
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
                    text = "Progress: ${currentQuestionIndex.intValue + 1} / ${quizModelFromConfigure.results.size}",
                    style = Typography.headlineSmall,
                    color = AppColors.white,
                )
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(
                        0.3f
                    )
            ) {
                Spacer(
                    modifier = Modifier.height(40.dp)
                )
                Text(
                    text = quizModelFromConfigure.results[currentQuestionIndex.intValue].question,
                    color = AppColors.white

                )
            }
            if (quizModelFromConfigure.results[currentQuestionIndex.intValue].type == "multiple") OptionsComposable(
                optionsList = questionOptions,
                answer = quizModelFromConfigure.results[currentQuestionIndex.intValue].correctAnswer,
                selectedOptionIndex = selectedOptionIndex,
                userChecksAnswer = userChecksAnswer.value,

                onClick = {
                }
            ) else {
                OptionsComposable(
                    selectedOptionIndex = selectedOptionIndex,
                    optionsList = questionOptions,
                    answer = quizModelFromConfigure.results[currentQuestionIndex.intValue].correctAnswer,
                    userChecksAnswer = userChecksAnswer.value,
                    onClick = {
                    }
                )
            }
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            if (!userHasChosen.value) CustomButton(
                buttonColor = AppColors.brown,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    // Check answer
                    userHasChosen.value = true
                    userChecksAnswer.value = true
                },
                text = "Check Answer"
            ) else
                CustomButton(
                    buttonColor = AppColors.brown,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                      if(currentQuestionIndex.intValue < questionOptions.size){
                          currentQuestionIndex.intValue++
                      } else  {
                          Toast.makeText(context, "End of questions list!", Toast.LENGTH_SHORT).show()
                      }
                        selectedOptionIndex.intValue = -1 // Reset selected option
                        userHasChosen.value = false
                        userChecksAnswer.value = false
                    },
                    text = "Next"
                )
        }
    }

}

@Composable
private fun OptionsComposable(
    userChecksAnswer: Boolean,
    optionsList: List<String>,
    answer: String,
    selectedOptionIndex: MutableState<Int>,
    onClick: () -> Unit = {},
) {
    val buttonColors = remember { mutableStateMapOf<Int, Color>() }
    if (userChecksAnswer) {
        //Logic to change color of options based on user's choice
        optionsList.forEachIndexed { index, option ->
            if (selectedOptionIndex.value == index) {
                if (option == answer) {
                    buttonColors[index] = Color.Green
                } else {
                    buttonColors[index] = Color.Red
                }
            }
        }
    } else {
        // Reset all button colors to default
        optionsList.forEachIndexed { index, _ ->
            buttonColors[index] = AppColors.lightPurple
        }
    }

    optionsList.forEachIndexed { index, option ->
        Column {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                color = buttonColors[index] ?: AppColors.lightPurple,
                shape = RoundedCornerShape(20.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable {
                            selectedOptionIndex.value = index
                            onClick()
                        }
                        .padding(10.dp)
                ) {
                    RadioButton(
                        selected = selectedOptionIndex.value == index,
                        onClick = { selectedOptionIndex.value = index },
                        modifier = Modifier.padding(16.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = option,
                        color = AppColors.white
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

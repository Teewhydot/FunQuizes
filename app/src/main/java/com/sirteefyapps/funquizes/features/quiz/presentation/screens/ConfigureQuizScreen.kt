package com.sirteefyapps.funquizes.features.quiz.presentation.screens

import Screens
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sirteefyapps.funquizes.data.data_source.KtorClient
import com.sirteefyapps.funquizes.data.models.QuizModel
import com.sirteefyapps.funquizes.features.quiz.presentation.widgets.CustomButton
import com.sirteefyapps.funquizes.features.quiz.presentation.widgets.DropdownButton
import com.sirteefyapps.funquizes.ui.theme.AppColors
import com.sirteefyapps.funquizes.ui.theme.Typography
import kotlinx.coroutines.launch

@Composable
fun ConfigureQuizScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    /// Selected category as a map where each category has a corresponding integer value
    val pickCategoryOptions = mapOf(
        "General Knowledge" to 9,
        "Entertainment: Books" to 10,
        "Entertainment: Film" to 11,
        "Entertainment: Music" to 12,
        "Entertainment: Musicals & Theatres" to 13,
        "Entertainment: Television" to 14,
        "Entertainment: Video Games" to 15,
        "Entertainment: Board Games" to 16,
        "Science & Nature" to 17,
        "Science: Computers" to 18,
        "Science: Mathematics" to 19,
        "Mythology" to 20,
        "Sports" to 21,
        "Geography" to 22,
        "History" to 23,
        "Politics" to 24,
        "Art" to 25,
        "Celebrities" to 26,
        "Animals" to 27,
        "Vehicles" to 28,
        "Entertainment: Comics" to 29,
        "Science: Gadgets" to 30,
        "Entertainment: Japanese Anime & Manga" to 31,
        "Entertainment: Cartoon & Animations" to 32
    )
    val selectDifficultyOptions = listOf("easy", "medium", "hard")
    val selectQuizTypeOptions = listOf("multiple", "boolean")
    val selectedCategoryOption = remember { mutableStateOf(pickCategoryOptions.keys.first()) }
    val selectedDifficultyOption = remember { mutableStateOf(selectDifficultyOptions[0]) }
    val selectedQuizTypeOption = remember { mutableStateOf(selectQuizTypeOptions[0]) }
    var questionsFromApi: QuizModel
    val isLoading = remember { mutableStateOf(false) }

    // Function to fetch quiz questions and handle the result
    fun fetchQuizQuestions() {
        coroutineScope.launch {
            try {
                isLoading.value = true
                // Call the suspending function and await the result
                questionsFromApi = KtorClient().getFunQuizQuestionsKtorClient(
                    amount = 50,
                    category = pickCategoryOptions[selectedCategoryOption.value]!!,
                    difficulty = selectedDifficultyOption.value,
                    type = selectedQuizTypeOption.value
                )
                // Check if the result is empty
                if (questionsFromApi.results.isEmpty()) {
                    // Show a toast if the result is empty
                    isLoading.value = false
                    Toast.makeText(context, "No questions found!", Toast.LENGTH_SHORT).show()
                } else {
                    // Navigate to QuizScreen with the result
                    isLoading.value = false
                    navController.navigate(Screens.Quiz(
                            quizModel = questionsFromApi)
                    )
                }
            } catch (e: Exception) {
                // Handle any errors (e.g., network issues)
                isLoading.value = false
                Log.e("ConfigureQuizScreen", "Error: ${e.message}")
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }



   if(!isLoading.value) Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppColors.darkPurple)
            .windowInsetsPadding(WindowInsets.safeContent),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            color = AppColors.darkPurple
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Configure Quiz",
                    style = Typography.headlineMedium,
                    color = AppColors.white,
                )
            }
        }
        Column(modifier = Modifier.padding(16.dp)) {
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            Text(
                text = "Choose Quiz Category",
                style = Typography.bodyMedium,
                color = AppColors.white,

                )
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            DropdownButton(
                options = pickCategoryOptions.keys.toList(),
                selectedOption = selectedCategoryOption.value,
                onOptionSelected = { selectedCategoryOption.value = it }
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            Text(
                text = "Choose Difficulty",
                style = Typography.bodyMedium,
                color = AppColors.white,
            )
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            DropdownButton(
                options = selectDifficultyOptions,
                selectedOption = selectedDifficultyOption.value,
                onOptionSelected = { selectedDifficultyOption.value = it }
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            Text(
                text = "Choose Quiz Mode",
                style = Typography.bodyMedium,
                color = AppColors.white,

                )
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            DropdownButton(
                options = selectQuizTypeOptions,
                selectedOption = selectedQuizTypeOption.value,
                onOptionSelected = { selectedQuizTypeOption.value = it }
            )
            Spacer(
                modifier = Modifier.height(50.dp)
            )
            CustomButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                buttonColor = AppColors.brown,
                onClick = {
                    fetchQuizQuestions()
                },
                text = "Start Quiz"
            )
        }
    }
    else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AppColors.darkPurple)
                .windowInsetsPadding(WindowInsets.safeContent),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Loading...",
                style = Typography.bodyMedium,
                color = AppColors.white
            )
        }
    }
}

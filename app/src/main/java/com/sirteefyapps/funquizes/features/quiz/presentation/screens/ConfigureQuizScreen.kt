package com.sirteefyapps.funquizes.features.quiz.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sirteefyapps.funquizes.data.data_source.KtorClient
import com.sirteefyapps.funquizes.features.quiz.presentation.widgets.CustomButton
import com.sirteefyapps.funquizes.features.quiz.presentation.widgets.DropdownButton
import com.sirteefyapps.funquizes.ui.theme.AppColors
import com.sirteefyapps.funquizes.ui.theme.Typography
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun ConfigureQuizScreen(navController: NavController,) {
    val selectCategoryOptions = listOf("General Knowledge", "Entertainment: Books", "Entertainment: Film","Entertainment: Music","Entertainment: Music",
        "Entertainment: Musicals & Theatres","Entertainment: Television","Entertainment: Video Games","Entertainment: Board Games","Science & Nature",
        "Science: Computers","Science: Mathematics","Mythology","Sports","Geography","History","Politics","Art","Celebrities","Animals","Vehicles","Entertainment: Comics",
        "Science: Gadgets","Entertainment: Japanese Anime & Manga","Entertainment: Cartoon & Animations"
    )
    val selectDifficultyOptions = listOf("easy", "medium", "hard")
    val selectQuizTypeOptions = listOf("multiple", "boolean")
    val selectedCategoryOption = remember { mutableStateOf(selectCategoryOptions[0]) }
    val selectedDifficultyOption = remember { mutableStateOf(selectDifficultyOptions[0]) }
    val selectedQuizTypeOption = remember { mutableStateOf(selectQuizTypeOptions[0]) }
//    val isLoading = funQuizViewModel.questionList.value.isLoading
    Column(
            modifier = Modifier.fillMaxSize().background(color = AppColors.darkPurple),
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth().height(60.dp),
                color = AppColors.lightPurple
            ) {
               Row (
                   modifier = Modifier.fillMaxWidth(),
                     horizontalArrangement = Arrangement.Center,
                   verticalAlignment = Alignment.CenterVertically
               ){
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
                    options = selectCategoryOptions,
                    selectedOption = selectedCategoryOption.value,
                    onOptionSelected = { selectedCategoryOption.value = it }
                )
                Spacer(
                    modifier = Modifier.height(20.dp))
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
                  modifier = Modifier.height(20.dp))
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
              CustomButton(modifier = Modifier.align(Alignment.CenterHorizontally), buttonColor = AppColors.brown, onClick = {
                  kotlinx.coroutines.GlobalScope.launch {
                     val questions = KtorClient().getFunQuizQuestionsKtorClient()
                  }
              }, text =  "Start Quiz"
              )
          }
        }
}

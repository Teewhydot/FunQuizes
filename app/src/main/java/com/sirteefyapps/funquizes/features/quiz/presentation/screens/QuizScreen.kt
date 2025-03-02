package com.sirteefyapps.funquizes.features.quiz.presentation.screens

import android.provider.MediaStore.Audio.Radio
import android.widget.Space
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sirteefyapps.funquizes.features.quiz.presentation.widgets.CustomButton
import com.sirteefyapps.funquizes.ui.theme.AppColors
import com.sirteefyapps.funquizes.ui.theme.Typography

@Composable
fun QuizScreen(navController: NavController) {
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
                   text = "How this ramadan go be, na to dey sleep all day?",
                   color = AppColors.white

               )
           }
          Column {
              OptionComposable(
                  selected = true,
                  text = "Maybe"
              )
              Spacer(
                  modifier = Modifier.height(10.dp)
              )
              OptionComposable(
                  text = "Read Quran",
                  onClick = {}
                  , selected = false
              )
              Spacer(
                  modifier = Modifier.height(10.dp)
              )
              OptionComposable(
                  text = "Pray",
                  onClick = {}
                  , selected = false
              )
          }
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            CustomButton(
               buttonColor = AppColors.brown,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {},
                text = "Check Answer"
            )
        }
    }

}

@Composable
private fun OptionComposable(selected: Boolean = false, onClick: () -> Unit = {}, text: String) {
 Surface(modifier = Modifier.fillMaxWidth().height(50.dp), color = AppColors.lightPurple, shape = RoundedCornerShape(20.dp)) {
     Row(
         horizontalArrangement = Arrangement.Start,
         verticalAlignment = Alignment.CenterVertically
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

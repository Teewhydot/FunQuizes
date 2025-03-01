package com.sirteefyapps.funquizes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sirteefyapps.funquizes.features.quiz.presentation.screens.ConfigureQuizScreen
import com.sirteefyapps.funquizes.features.quiz.presentation.screens.QuizScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                FunQuizHome(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun FunQuizHome(modifier: Modifier = Modifier) {
  ConfigureQuizScreen()
}

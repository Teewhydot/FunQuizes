package com.sirteefyapps.funquizes.features.quiz.presentation.widgets

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sirteefyapps.funquizes.ui.theme.AppColors
import com.sirteefyapps.funquizes.ui.theme.Typography


@Composable
fun CustomButton(modifier: Modifier) {
    ElevatedButton(
        onClick = { }, colors = ButtonColors(
            containerColor = AppColors.brown,
            contentColor = AppColors.white,
            disabledContentColor = AppColors.white,
            disabledContainerColor = AppColors.brown,
        ), modifier = modifier
    ) {
        Text(
            text = "Start Quiz",
            style = Typography.bodyMedium,
            color = AppColors.white,
        )
    }
}

package com.sirteefyapps.funquizes.features.quiz.presentation.widgets

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sirteefyapps.funquizes.ui.theme.AppColors
import com.sirteefyapps.funquizes.ui.theme.Typography


@Composable
fun CustomButton(modifier: Modifier, buttonColor: Color) {
    ElevatedButton(
        onClick = { }, colors = ButtonColors(
            containerColor = buttonColor,
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

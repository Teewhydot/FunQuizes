package com.sirteefyapps.funquizes.features.quiz.presentation.widgets


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sirteefyapps.funquizes.ui.theme.AppColors
import com.sirteefyapps.funquizes.ui.theme.Typography

@Composable
fun DropdownButton(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    val selected = remember { mutableStateOf(selectedOption) }
    val expanded = remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth()) {
        Surface(modifier = Modifier.fillMaxWidth().border(
            border = BorderStroke(
                width = 1.dp,
                color = AppColors.brown,
            ),
            shape = RoundedCornerShape(20.dp)
        ),
            color = AppColors.lightPurple, shape = RoundedCornerShape(20.dp)) {
            Text(
                text = selectedOption,
                style = Typography.bodySmall.copy(
                    color = AppColors.white
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded.value = true }
                    .padding(16.dp)
            )
        }
        DropdownMenu(
            expanded = expanded.value,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            onDismissRequest = { expanded.value = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        selected.value = option
                        onOptionSelected(option)
                        expanded.value = false
                    },
                    text = { Text(option) },
                )
            }
        }
    }
}

package com.sirteefyapps.funquizes.features.quiz.presentation.widgets


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DropdownButton(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    val selected = remember { mutableStateOf(selectedOption) }
    val expanded = remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = selectedOption,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded.value = true }
                .padding(16.dp)
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        selected.value = option
                        onOptionSelected(option)
                        expanded.value = false
                    },
                    text = TODO(),
                    modifier = TODO(),
                    leadingIcon = TODO(),
                    trailingIcon = TODO(),
                    enabled = TODO(),
                    colors = TODO(),
                    contentPadding = TODO()
                )
            }
        }
    }
}

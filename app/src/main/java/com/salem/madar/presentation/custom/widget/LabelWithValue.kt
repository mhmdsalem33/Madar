package com.salem.madar.presentation.custom.widget
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LabelWithValue(label: String, value: String) {
    Row(modifier = Modifier.padding(bottom = 4.dp)) {
        Text(
            text = "$label ",
            style = MaterialTheme.typography.body1,
            color = Color.Black
        )
        Text(
            text = value,
            style = MaterialTheme.typography.body1,
            color = Color.DarkGray
        )
    }
}
package com.salem.madar.presentation.custom.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salem.madar.R

@Composable
fun CusttomButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    textResId: Int = R.string.save // default value
) {
    val text = stringResource(id = textResId)

    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.blue_transparent)
        ),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp) // No shadow
    ) {

        Text(
            text = text ,
            style = MaterialTheme.typography.body1.copy(fontSize = 16.sp),
            color = Color.White
        )
    }
}
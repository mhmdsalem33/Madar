package com.salem.madar.presentation.custom.widget

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.salem.madar.R
import com.salem.madar.presentation.ui.compose.theme.sofiaProLight

@Composable
fun TextSofiaPro(
    text : String = stringResource(id = R.string.enter_your_name)
){
    Text(
        text = text ,
        fontFamily = sofiaProLight(),
        color = colorResource(id = R.color.gray_4),
        fontWeight = FontWeight.Bold
    )

}
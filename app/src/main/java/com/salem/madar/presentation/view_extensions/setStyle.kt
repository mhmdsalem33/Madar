package com.salem.madar.presentation.view_extensions

import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun TextView.setStyle(@DrawableRes bgRes: Int, @ColorRes textColorRes: Int) {
    background = ContextCompat.getDrawable(context, bgRes)
    setTextColor(ContextCompat.getColor(context, textColorRes))
}
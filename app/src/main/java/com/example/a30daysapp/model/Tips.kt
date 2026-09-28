package com.example.a30daysapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Tips(
    @StringRes val day: Int,
    @StringRes val title: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val description: Int
)

package com.example.healthydessertsanddrinks

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DessertItem(
    val name: String,
    val calories: Int,
    val imageResId: Int,
    var isFavorite: Boolean = false // Состояние избранного
) : Parcelable

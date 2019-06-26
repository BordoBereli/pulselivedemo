package com.kutluoglu.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by F.K. on 2019-06-25.
 *
 */

@Parcelize
data class ContentView (
    val id: Int,
    val title: String,
    val subtitle: String,
    val date: String
) : Parcelable
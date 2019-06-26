package com.kutluoglu.cache.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.kutluoglu.cache.database.db.constant.DbConstants
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Created by F.K. on 2019-06-25.
 *
 */

@Parcelize
@Entity(tableName = DbConstants.TABLE_NAME_CONTENT_DETAIL, primaryKeys = ["contentId"])

data class ContentDetailEntity (
    @ColumnInfo(name = "contentId") val contentId: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "subTitle") val subTitle: String,
    @ColumnInfo(name = "body") val body: String,
    @ColumnInfo(name = "date") val date: Date
) : Parcelable
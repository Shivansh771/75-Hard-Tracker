package com.example.hard75tracker.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName="button_state")
class buttonState (
    @ColumnInfo val state:BooleanArray,
    @ColumnInfo var num:Int
        ) : Parcelable
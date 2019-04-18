package com.example.todoapp

import android.os.Parcelable
import java.io.Serializable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(
    val name: String,
    val surname: String
): Parcelable


package com.example.bitfit1

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Keep
@Serializable
data class LogItem(
    @SerialName("date")
    val date: String?,
    @SerialName("hours")
    val hours: String?,
) : java.io.Serializable

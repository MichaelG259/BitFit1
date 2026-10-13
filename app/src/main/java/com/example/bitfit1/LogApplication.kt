package com.example.bitfit1

import android.app.Application

class LogApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}
package com.example.hard75tracker.application

import android.app.Application
import com.example.hard75tracker.database.buttonStateDatabase
import com.example.hard75tracker.database.buttonStateRepository

class buttonStateApplication:Application() {
    private val database by lazy { buttonStateDatabase.getDatabase(this@buttonStateApplication) }
    val repository by lazy { buttonStateRepository(database.buttonStateDao()) }
}
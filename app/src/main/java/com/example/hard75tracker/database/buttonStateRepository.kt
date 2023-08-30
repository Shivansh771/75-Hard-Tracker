package com.example.hard75tracker.database

import androidx.annotation.WorkerThread
import com.example.hard75tracker.entities.buttonState
import kotlinx.coroutines.flow.Flow

class buttonStateRepository (private val buttonStateDAO: buttonStateDAO){
    @WorkerThread
    suspend fun insertButtonState(buttonState: buttonState){
        buttonStateDAO.insert(buttonState)

    }
    val buttonStateList: Flow<List<buttonState>> =buttonStateDAO.getButtonState()

    @WorkerThread
    suspend fun updateButtonState(buttonState: buttonState){
        buttonStateDAO.update(buttonState)
    }
    @WorkerThread
    suspend fun delete(buttonState: buttonState){
        buttonStateDAO.delete(buttonState)
    }
}
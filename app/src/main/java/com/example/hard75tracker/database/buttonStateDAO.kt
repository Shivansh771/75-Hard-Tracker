package com.example.hard75tracker.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.hard75tracker.entities.buttonState
import kotlinx.coroutines.flow.Flow

@Dao
interface buttonStateDAO {
    @Insert
    fun insert(buttonState: buttonState)
    @Query("SELECT * FROM BUTTON_STATE ")
    fun getButtonState():Flow<List<buttonState>>

    @Update
    fun update(buttonState: buttonState)

    @Delete
    fun delete(buttonState: buttonState)

}
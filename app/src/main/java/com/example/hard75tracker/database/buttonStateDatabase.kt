package com.example.hard75tracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hard75tracker.entities.buttonState

@Database(entities = [buttonState::class],version=1)
abstract class buttonStateDatabase:RoomDatabase(){
    abstract fun buttonStateDao():buttonStateDAO
    companion object{
        @Volatile
        private var INSTANCE: buttonStateDatabase?=null
        fun getDatabase(context: Context):buttonStateDatabase{

            return INSTANCE?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    buttonStateDatabase::class.java,
                    "button_state"
                ).fallbackToDestructiveMigration().build()
                INSTANCE=instance
                instance
            }
        }

    }
}
package com.example.hard75tracker.ui.home

import androidx.lifecycle.*
import com.example.hard75tracker.database.buttonStateRepository
import com.example.hard75tracker.entities.buttonState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val repository: buttonStateRepository) : ViewModel() {
    fun insert(buttonState: buttonState)=viewModelScope.launch{
        withContext(Dispatchers.IO) {
            repository.insertButtonState(buttonState)
        }
    }
    val buttonStateList:LiveData<List<buttonState>> = repository.buttonStateList.asLiveData()

    fun update(buttonState: buttonState)=viewModelScope.launch {
        withContext(Dispatchers.IO){
            repository.updateButtonState(buttonState)
        }
    }
    fun delete(buttonState: buttonState)=viewModelScope.launch {
        withContext(Dispatchers.IO){
            repository.delete(buttonState)
        }
    }

}
class buttonStateViewModelFactory(private val repository: buttonStateRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel Class")

    }
}
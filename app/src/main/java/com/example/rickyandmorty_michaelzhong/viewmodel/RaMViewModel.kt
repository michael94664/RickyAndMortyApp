package com.example.rickyandmorty_michaelzhong.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickyandmorty_michaelzhong.model.Repository
import com.example.rickyandmorty_michaelzhong.model.data.Character
import com.example.rickyandmorty_michaelzhong.util.Logger.Companion.logError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RaMViewModel: ViewModel() {

    private val repository = Repository()

    private var netJob: Job? = null

    private val imageData: MutableLiveData<List<Character>> = MutableLiveData()
    fun getData(): LiveData<List<Character>> = imageData

    fun getCharacters(){
        netJob = viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getCharactersOnlineAsync().await()
                repository.saveForOffline(response)
                imageData.postValue(response.characters)
            } catch (e: Exception) {
                //At this point read from database...
                logError(e.toString())
                imageData.postValue(repository.getCharactersOffline().characters)
            }
        }
    }

    override fun onCleared() {
        netJob?.cancel()
        super.onCleared()
    }
}
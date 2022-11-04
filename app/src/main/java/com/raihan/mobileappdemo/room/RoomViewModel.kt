package com.raihan.mobileappdemo.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RoomViewModel(
    application: Application,

    ) : AndroidViewModel(application) {
    val readAllData: LiveData<List<MovieRoomModel>>
    lateinit var readList: LiveData<List<MovieRoomModel>>
    lateinit var readSingle: LiveData<MovieRoomModel>
    private val repository: RoomRepository


    init {
        val roomDao = DemoMobileDatabase.getDatabase(application).roomDao()

        repository = RoomRepository(roomDao)
        readAllData = repository.readAllData

    }

    fun insertData(movieRoomModel: MovieRoomModel) {
        GlobalScope.launch {
            repository.insertData(movieRoomModel)
        }

    }

    fun updateData(movieRoomModel: MovieRoomModel) {
        GlobalScope.launch {
            repository.updateData(movieRoomModel)

        }
    }

    /*
        fun updateData(movieRoomModel: MovieRoomModel) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.updateData(movieRoomModel)

            }
        }

        fun deleteallData() {
            viewModelScope.launch(Dispatchers.IO) {
                repository.deleteallData()
            }
        }
    */
    fun readList(type: String): LiveData<List<MovieRoomModel>> {
        readList = repository.readList(type)
        return readList
    }

    fun readSingle(type: String): LiveData<MovieRoomModel> {
        readSingle = repository.readSingle(type)
        return readSingle
    }


}
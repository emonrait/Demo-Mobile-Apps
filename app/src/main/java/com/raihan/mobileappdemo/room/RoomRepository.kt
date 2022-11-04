package com.raihan.mobileappdemo.room

import androidx.lifecycle.LiveData

class RoomRepository(
    private val roomDao: RoomDao,
) {

    val readAllData: LiveData<List<MovieRoomModel>> = roomDao.readAllData()


    lateinit var readList: LiveData<List<MovieRoomModel>>
    lateinit var readSingle: LiveData<MovieRoomModel>

    suspend fun insertData(movieRoomModel: MovieRoomModel) {
        roomDao.insertData(movieRoomModel)
    }

    suspend fun updateData(movieRoomModel: MovieRoomModel) {
        roomDao.updateData(movieRoomModel)
    }

    suspend fun deleteallData() {
        roomDao.deleteallData()
    }

    fun readList(type: String): LiveData<List<MovieRoomModel>> {
        readList = roomDao.readList(type)
        return readList
    }

    fun readSingle(type: String): LiveData<MovieRoomModel> {
        readSingle = roomDao.readSingle(type)
        return readSingle
    }

}
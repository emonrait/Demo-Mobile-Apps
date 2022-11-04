package com.raihan.mobileappdemo.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "room_movie", indices = arrayOf(
        Index(
            value = ["sl", "movieId"],
            unique = true
        )
    )
)
class MovieRoomModel(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "sl")
    var sl: Int = 0,

    @ColumnInfo(name = "movieId")
    var movieId: String = "",

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "rating")
    var rating: String = "",

    @ColumnInfo(name = "duration")
    var duration: String = "",

    @ColumnInfo(name = "genre")
    var genre: String = "",

    @ColumnInfo(name = "releasedDate")
    var releasedDate: String = "",

    @ColumnInfo(name = "trailerLink")
    var trailerLink: String = "",

    @ColumnInfo(name = "watchFlag")
    var watchFlag: String = "",

    ) : Parcelable
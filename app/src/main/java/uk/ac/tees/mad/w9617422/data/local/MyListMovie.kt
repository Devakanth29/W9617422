package uk.ac.tees.mad.w9617422.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watch_list_table")
data class MyListMovie(
    @PrimaryKey val mediaId: Int,
    val imagePath: String?,
    val title: String,
    val releaseDate: String,
    val rating: Double,
    val addedOn: String
)

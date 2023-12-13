package uk.ac.tees.mad.w9617422.data.repository

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.w9617422.data.local.MyListMovie
import uk.ac.tees.mad.w9617422.data.local.WatchListDatabase
import javax.inject.Inject

class WatchListRepository @Inject constructor(private val database: WatchListDatabase) {
    suspend fun addToWatchList(movie: MyListMovie){
        database.moviesDao.addToWatchList(movie)
    }

    suspend fun exists(mediaId: Int): Int{
        return database.moviesDao.exists(mediaId)
    }

    suspend fun removeFromWatchList(mediaId: Int){
        database.moviesDao.removeFromWatchList(mediaId)
    }

    fun getFullWatchList(): Flow<List<MyListMovie>> {
       return database.moviesDao.getFullWatchList()
    }

    suspend fun deleteWatchList(){
        database.moviesDao.deleteWatchList()
    }
}
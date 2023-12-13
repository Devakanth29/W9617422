package uk.ac.tees.mad.w9617422.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@AutoMigration(from = 1, to = 2)
@Database(
    version = 1,
    entities = [MyListMovie::class],
    exportSchema = false,
)
abstract class WatchListDatabase : RoomDatabase() {
    abstract val moviesDao: MoviesDao
}

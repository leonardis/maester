package example.maester.utils

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import example.maester.dao.MoviesResultDao
import example.maester.dao.SeriesResultDao
import example.maester.models.MoviesResult
import example.maester.models.SeriesResult

@Database(entities = [ MoviesResult::class, SeriesResult::class ], version = 1)
abstract class MaesterDatabase: RoomDatabase() {

    abstract fun getMovieResultsDao(): MoviesResultDao
    abstract fun getSeriesResultsDao(): SeriesResultDao
}
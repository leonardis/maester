package example.maester.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import example.maester.models.MoviesResult
import io.reactivex.Single

@Dao
interface MoviesResultDao {

    @Query("SELECT * FROM moviesresult")
    fun getAllMovies(): Single<List<MoviesResult>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MoviesResult>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(moviesResult: MoviesResult)
}
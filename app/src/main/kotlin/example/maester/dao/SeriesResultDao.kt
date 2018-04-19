package example.maester.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import example.maester.models.SeriesResult
import io.reactivex.Single

@Dao
interface SeriesResultDao {

    @Query("SELECT * FROM seriesresult")
    fun getAllSeries(): Single<List<SeriesResult>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(series: List<SeriesResult>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(seriesResult: SeriesResult)
}
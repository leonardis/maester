package example.maester.models
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class SeriesListResponse(
		@SerializedName("page") val page: Int,
		@SerializedName("total_results") val totalResults: Int?,
		@SerializedName("total_pages") val totalPages: Int?,
		@SerializedName("results") val seriesResults: List<SeriesResult>
)

@Entity(tableName = "seriesresult")
data class SeriesResult(
        @SerializedName("original_name") val originalName: String,
        @SerializedName("name") val name: String,
        @SerializedName("popularity") val popularity: Double,
        @SerializedName("vote_count") val voteCount: Int,
        @SerializedName("first_air_date") val firstAirDate: String,
        @SerializedName("backdrop_path") val backdropPath: String,
        @SerializedName("original_language") val originalLanguage: String,
        @PrimaryKey @SerializedName("id") val id: Int,
        @SerializedName("vote_average") val voteAverage: Double,
        @SerializedName("overview") val overview: String,
        @SerializedName("poster_path") val posterPath: String
)
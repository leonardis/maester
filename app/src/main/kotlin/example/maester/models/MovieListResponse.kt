package example.maester.models
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class MoviesListResponse(
		@SerializedName("page") val page: Int,
		@SerializedName("total_results") val totalResults: Int?,
		@SerializedName("total_pages") val totalPages: Int?,
		@SerializedName("results") val moviesResults: List<MoviesResult>
)

@Entity(tableName = "moviesresult")
data class MoviesResult(
        @SerializedName("vote_count") val voteCount: Int,
        @PrimaryKey @SerializedName("id") val id: Int,
        @SerializedName("video") val video: Boolean,
        @SerializedName("vote_average") val voteAverage: Double,
        @SerializedName("title") val title: String,
        @SerializedName("popularity") val popularity: Double,
        @SerializedName("poster_path") val posterPath: String,
        @SerializedName("original_language") val originalLanguage: String,
        @SerializedName("original_title") val originalTitle: String,
        @SerializedName("backdrop_path") val backdropPath: String,
        @SerializedName("adult") val adult: Boolean,
        @SerializedName("overview") val overview: String,
        @SerializedName("release_date") val releaseDate: String
)
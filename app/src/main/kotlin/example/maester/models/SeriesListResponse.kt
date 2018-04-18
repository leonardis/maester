package example.maester.models
import com.google.gson.annotations.SerializedName

data class SeriesListResponse(
		@SerializedName("page") val page: Int,
		@SerializedName("total_results") val totalResults: Int?,
		@SerializedName("total_pages") val totalPages: Int?,
		@SerializedName("results") val seriesResults: List<SeriesResult>
)

data class SeriesResult(
		@SerializedName("original_name") val originalName: String,
		@SerializedName("genre_ids") val genreIds: List<Int>,
		@SerializedName("name") val name: String,
		@SerializedName("popularity") val popularity: Double,
		@SerializedName("origin_country") val originCountry: List<String>,
		@SerializedName("vote_count") val voteCount: Int,
		@SerializedName("first_air_date") val firstAirDate: String,
		@SerializedName("backdrop_path") val backdropPath: String,
		@SerializedName("original_language") val originalLanguage: String,
		@SerializedName("id") val id: Int,
		@SerializedName("vote_average") val voteAverage: Double,
		@SerializedName("overview") val overview: String,
		@SerializedName("poster_path") val posterPath: String
)
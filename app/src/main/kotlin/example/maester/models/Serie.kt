package example.maester.models
import com.google.gson.annotations.SerializedName

data class Serie(
		@SerializedName("backdrop_path") val backdropPath: String,
		@SerializedName("created_by") val createdBy: List<CreatedBy>,
		@SerializedName("episode_run_time") val episodeRunTime: List<Int>,
		@SerializedName("first_air_date") val firstAirDate: String,
		@SerializedName("genres") val genres: List<Genre>,
		@SerializedName("homepage") val homepage: String,
		@SerializedName("id") val id: Int,
		@SerializedName("in_production") val inProduction: Boolean,
		@SerializedName("languages") val languages: List<String>,
		@SerializedName("last_air_date") val lastAirDate: String,
		@SerializedName("name") val name: String,
		@SerializedName("networks") val networks: List<Network>,
		@SerializedName("number_of_episodes") val numberOfEpisodes: Int,
		@SerializedName("number_of_seasons") val numberOfSeasons: Int,
		@SerializedName("origin_country") val originCountry: List<String>,
		@SerializedName("original_language") val originalLanguage: String,
		@SerializedName("original_name") val originalName: String,
		@SerializedName("overview") val overview: String,
		@SerializedName("popularity") val popularity: Double,
		@SerializedName("poster_path") val posterPath: String,
		@SerializedName("production_companies") val productionCompanies: List<ProductionCompany>,
		@SerializedName("seasons") val seasons: List<Season>,
		@SerializedName("status") val status: String,
		@SerializedName("type") val type: String,
		@SerializedName("vote_average") val voteAverage: Double,
		@SerializedName("vote_count") val voteCount: Int
)

data class Season(
		@SerializedName("air_date") val airDate: Any,
		@SerializedName("episode_count") val episodeCount: Int,
		@SerializedName("id") val id: Int,
		@SerializedName("name") val name: String,
		@SerializedName("overview") val overview: String,
		@SerializedName("poster_path") val posterPath: String,
		@SerializedName("season_number") val seasonNumber: Int
)

data class Network(
		@SerializedName("name") val name: String,
		@SerializedName("id") val id: Int,
		@SerializedName("logo_path") val logoPath: String,
		@SerializedName("origin_country") val originCountry: String
)

data class CreatedBy(
		@SerializedName("id") val id: Int,
		@SerializedName("name") val name: String,
		@SerializedName("gender") val gender: Int,
		@SerializedName("profile_path") val profilePath: String
)
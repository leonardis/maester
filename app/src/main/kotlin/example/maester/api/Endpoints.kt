package example.maester.api

import example.maester.models.MoviesListResponse
import example.maester.models.Movie
import example.maester.models.Serie
import example.maester.models.SeriesListResponse
import example.maester.utils.API_KEY
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoints {

    @GET("movie/popular?&api_key=$API_KEY&language=en-US&page=1")
    fun popularMovies(): Observable<MoviesListResponse>

    @GET("movie/top_rated?&api_key=$API_KEY&language=en-US&page=1")
    fun topRatedMovies(): Observable<MoviesListResponse>

    @GET("movie/upcoming?&api_key=$API_KEY&language=en-US&page=1")
    fun upcomingMovies(): Observable<MoviesListResponse>

    @GET("movie/{id}?api_key=$API_KEY&language=en-US")
    fun movieDetail(@Path("id") id: String): Observable<Movie>

    @GET("movie/{id}/similar?api_key=$API_KEY&language=en-US")
    fun similarMovies(@Path("id") id: String): Observable<MoviesListResponse>

    @GET("tv/popular?&api_key=$API_KEY&language=en-US&page=1")
    fun popularSeries(): Observable<SeriesListResponse>

    @GET("tv/top_rated?&api_key=$API_KEY&language=en-US&page=1")
    fun topRatedSeries(): Observable<SeriesListResponse>

    @GET("tv/airing_today?&api_key=$API_KEY&language=en-US&page=1")
    fun airingToday(): Observable<SeriesListResponse>

    @GET("tv/{id}?api_key=$API_KEY&language=en-US")
    fun serieDetail(@Path("id") id: String): Observable<Serie>

    @GET("search/movie?&api_key=$API_KEY&language=en-US&page=1")
    fun searchMovie(@Query("query")query: String): Observable<MoviesListResponse>

    @GET("search/tv?&api_key=$API_KEY&language=en-US&page=1")
    fun searchSeries(@Query("query")query: String): Observable<SeriesListResponse>
}
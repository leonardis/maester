package example.maester.ui.detail.presenter

import example.maester.base.mvp.BaseView
import example.maester.models.Movie
import example.maester.models.MoviesResult
import example.maester.models.Serie

interface DetailView : BaseView {
    fun onMovieResponse(movie: Movie)
    fun onSerieResponse(serie: Serie)
    fun onSimilarMovieResponse(list: List<MoviesResult>)
    fun showProgress()
    fun hideProgress()
    fun noResult()
}

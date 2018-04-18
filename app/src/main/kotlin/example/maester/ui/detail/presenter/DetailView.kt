package example.maester.ui.detail.presenter

import example.maester.base.mvp.BaseView
import example.maester.models.Movie
import example.maester.models.MoviesResult

interface DetailView : BaseView {
    fun onResponse(movie: Movie)
    fun onSimilarMovieResponse(list: List<MoviesResult>)
    fun showProgress()
    fun hideProgress()
    fun noResult()
}

package example.maester.ui.search.presenter

import example.maester.base.mvp.BaseView
import example.maester.models.MoviesResult
import example.maester.models.SeriesResult

interface SearchView : BaseView {
    fun onMovieResponse(list: List<MoviesResult>)
    fun onSeriesResponse(list: List<SeriesResult>)
    fun showProgress()
    fun hideProgress()
    fun noResult()
}

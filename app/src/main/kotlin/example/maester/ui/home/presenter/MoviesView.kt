package example.maester.ui.home.presenter

import example.maester.base.mvp.BaseView
import example.maester.models.MoviesResult

interface MoviesView : BaseView {
    fun onResponse(list: List<MoviesResult>, type: Int)
    fun showProgress()
    fun hideProgress()
    fun noResult()
}

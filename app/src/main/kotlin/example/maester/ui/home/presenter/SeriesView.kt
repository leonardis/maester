package example.maester.ui.home.presenter

import example.maester.base.mvp.BaseView
import example.maester.models.SeriesResult

interface SeriesView : BaseView {
    fun onResponse(list: List<SeriesResult>, type: Int)
    fun showProgress()
    fun hideProgress()
    fun noResult()
}

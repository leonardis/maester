package example.maester.ui.home.presenter

import example.maester.api.Endpoints
import example.maester.base.App
import example.maester.base.mvp.BasePresenter
import example.maester.models.SeriesResult
import example.maester.utils.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SeriesPresenter @Inject constructor(var api: Endpoints, disposable: CompositeDisposable,
                                          scheduler: SchedulerProvider) : BasePresenter<SeriesView>
(disposable, scheduler) {

    fun getPopulars() {
        view?.showProgress()
        Observable
                .concatArray(getSeriesFromDb(), getPopularsFromApi())
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({ populars ->
                    view?.hideProgress()
                    view?.onResponse(populars, 1)
                }, { _ ->
                    view?.hideProgress()
                    view?.onError()
                })
    }

    fun getTopRated() {
        view?.showProgress()
        Observable
                .concatArray(getSeriesFromDb(), getTopRatedFromApi())
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({ populars ->
                    view?.hideProgress()
                    view?.onResponse(populars, 2)

                }, { _ ->
                    view?.hideProgress()
                    view?.onError()
                })
    }

    fun getAiringToday() {
        view?.showProgress()
        Observable
                .concatArray(getSeriesFromDb(), getAiringFromApi())
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({ populars ->
                    view?.hideProgress()
                    view?.onResponse(populars, 3)

                }, { error ->
                    error.printStackTrace()
                    view?.hideProgress()
                    view?.onError()
                })
    }

    private fun getPopularsFromApi(): Observable<List<SeriesResult>> {
        return api.popularSeries()
                .map { it -> it.seriesResults }
                .doOnNext { storeMoviesOnDb(it) }
    }

    private fun getTopRatedFromApi(): Observable<List<SeriesResult>> {
        return api.topRatedSeries()
                .map { it -> it.seriesResults }
                .doOnNext { storeMoviesOnDb(it) }
    }

    private fun getAiringFromApi(): Observable<List<SeriesResult>> {
        return api.airingToday()
                .map { it -> it.seriesResults }
                .doOnNext { storeMoviesOnDb(it) }
    }

    private fun getSeriesFromDb(): Observable<List<SeriesResult>> {
        return App.database!!
                .getSeriesResultsDao()
                .getAllSeries()
                .toObservable()
    }

    private fun storeMoviesOnDb(seriesResult: List<SeriesResult>) {
        Observable.fromCallable { App.database!!.getSeriesResultsDao().insertAll(seriesResult) }
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
    }
}
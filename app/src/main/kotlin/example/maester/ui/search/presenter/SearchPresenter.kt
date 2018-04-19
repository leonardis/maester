package example.maester.ui.search.presenter

import example.maester.api.Endpoints
import example.maester.base.mvp.BasePresenter
import example.maester.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SearchPresenter @Inject constructor(var api: Endpoints, disposable: CompositeDisposable,
                                          scheduler: SchedulerProvider) : BasePresenter<SearchView>
(disposable, scheduler) {

    fun searchMovie(query: String) {
        view?.showProgress()
        disposable.add(api.searchMovie(query)
                .map { it -> it.moviesResults }
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe(
                        { movie ->
                            view?.hideProgress()
                            if (movie != null) {
                                view?.onMovieResponse(movie)
                            } else {
                                view?.noResult()
                            }
                        },
                        { _ ->
                            view?.hideProgress()
                            view?.onError()
                        })
        )
    }

    fun searchSeries(query: String) {
        view?.showProgress()
        disposable.add(api.searchSeries(query)
                .map{ it -> it.seriesResults }
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe(
                        { series ->
                            view?.hideProgress()
                            if (series != null) {
                                view?.onSeriesResponse(series)
                            } else {
                                view?.noResult()
                            }
                        },
                        { _ ->
                            view?.hideProgress()
                            view?.onError()
                        })
        )
    }
}
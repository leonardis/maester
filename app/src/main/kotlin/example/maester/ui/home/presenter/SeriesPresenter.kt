package example.maester.ui.home.presenter

import example.maester.api.Endpoints
import example.maester.base.mvp.BasePresenter
import example.maester.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SeriesPresenter @Inject constructor(var api: Endpoints, disposable: CompositeDisposable,
                                          scheduler: SchedulerProvider) : BasePresenter<SeriesView>
(disposable, scheduler) {

    fun getPopulars() {
        view?.showProgress()
        disposable.add(api.popularSeries()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe(
                        { result ->
                            view?.hideProgress()
                            view?.onResponse(result.seriesResults, 1)

                            if (result.seriesResults == null || result.seriesResults.isEmpty()) {
                                view?.noResult()
                            }
                        },
                        { _ ->
                            view?.hideProgress()
                            view?.onError()
                        })
        )
    }

    fun getTopRated() {
        view?.showProgress()
        disposable.add(api.topRatedSeries()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe(
                        { result ->
                            view?.hideProgress()
                            view?.onResponse(result.seriesResults, 2)

                            if (result.seriesResults == null || result.seriesResults.isEmpty()) {
                                view?.noResult()
                            }
                        },
                        { _ ->
                            view?.hideProgress()
                            view?.onError()
                        })
        )
    }

    fun getAiringToday() {
        view?.showProgress()
        disposable.add(api.airingToday()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe(
                        { result ->
                            view?.hideProgress()
                            view?.onResponse(result.seriesResults, 3)

                            if (result.seriesResults == null || result.seriesResults.isEmpty()) {
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
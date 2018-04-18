package example.maester.ui.home.presenter

import example.maester.api.Endpoints
import example.maester.base.mvp.BasePresenter
import example.maester.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MoviesPresenter @Inject constructor(var api: Endpoints, disposable: CompositeDisposable, scheduler: SchedulerProvider) : BasePresenter<MoviesView>(disposable, scheduler) {

    fun getPopulars() {
        view?.showProgress()
        disposable.add(api.popularMovies()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe(
                        { result ->
                            view?.hideProgress()
                            view?.onResponse(result.moviesResults, 1)

                            if (result.moviesResults == null || result.moviesResults.isEmpty()) {
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
        disposable.add(api.topRatedMovies()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe(
                        { result ->
                            view?.hideProgress()
                            view?.onResponse(result.moviesResults, 2)

                            if (result.moviesResults == null || result.moviesResults.isEmpty()) {
                                view?.noResult()
                            }
                        },
                        { _ ->
                            view?.hideProgress()
                            view?.onError()
                        })
        )
    }

    fun getUpcoming() {
        view?.showProgress()
        disposable.add(api.upcomingMovies()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe(
                        { result ->
                            view?.hideProgress()
                            view?.onResponse(result.moviesResults, 3)

                            if (result.moviesResults == null || result.moviesResults.isEmpty()) {
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
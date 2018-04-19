package example.maester.ui.home.presenter

import android.util.Log
import example.maester.api.Endpoints
import example.maester.base.App
import example.maester.base.mvp.BasePresenter
import example.maester.models.MoviesListResponse
import example.maester.models.MoviesResult
import example.maester.utils.DEBUG_TAG
import example.maester.utils.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MoviesPresenter @Inject constructor(var api: Endpoints, disposable: CompositeDisposable, scheduler: SchedulerProvider) : BasePresenter<MoviesView>(disposable, scheduler) {

    fun getPopulars() {
        view?.showProgress()
        Observable
                .concatArray(getMoviesFromDb(), getPopularsFromApi())
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
                .concatArray(getMoviesFromDb(), getTopRatedFromApi())
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({ topRated ->
                    view?.hideProgress()
                    view?.onResponse(topRated, 2)

                }, { _ ->
                    view?.hideProgress()
                    view?.onError()
                })
    }

    fun getUpcoming() {
        view?.showProgress()
        Observable
                .concatArray(getMoviesFromDb(), getUpcomingFromApi())
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({ upcoming ->
                    view?.hideProgress()
                    view?.onResponse(upcoming, 3)
                }, { error ->
                    error.printStackTrace()
                    view?.hideProgress()
                    view?.onError()
                })
    }

    private fun getPopularsFromApi(): Observable<List<MoviesResult>> {
        return api.popularMovies()
                .map { it -> it.moviesResults }
                .doOnNext { storeMoviesOnDb(it) }
    }

    private fun getTopRatedFromApi(): Observable<List<MoviesResult>> {
        return api.topRatedMovies()
                .map { it -> it.moviesResults }
                .doOnNext { storeMoviesOnDb(it) }
    }

    private fun getUpcomingFromApi(): Observable<List<MoviesResult>> {
        return api.upcomingMovies()
                .map { it -> it.moviesResults }
                .doOnNext { storeMoviesOnDb(it) }
    }

    private fun getMoviesFromDb(): Observable<List<MoviesResult>> {
        return App.database!!
                .getMovieResultsDao()
                .getAllMovies()
                .toObservable()
    }

    private fun storeMoviesOnDb(moviesResult: List<MoviesResult>) {
        Observable.fromCallable { App.database!!.getMovieResultsDao().insertAll(moviesResult) }
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
    }
}
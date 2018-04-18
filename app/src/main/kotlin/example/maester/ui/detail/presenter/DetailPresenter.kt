package example.maester.ui.detail.presenter

import example.maester.api.Endpoints
import example.maester.base.mvp.BasePresenter
import example.maester.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailPresenter @Inject constructor(var api: Endpoints, disposable: CompositeDisposable,
                                          scheduler: SchedulerProvider) : BasePresenter<DetailView>
(disposable, scheduler) {

    fun getMovieDetail(id: String) {
        view?.showProgress()
        disposable.add(api.movieDetail(id)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe(
                        { movie ->
                            view?.hideProgress()
                            if (movie != null) {
                                view?.onResponse(movie)
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

    fun getSimilarMovies(id: String) {
        view?.showProgress()
        disposable.add(api.similarMovies(id)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe(
                        { result ->
                            view?.hideProgress()
                            view?.onSimilarMovieResponse(result.moviesResults)

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
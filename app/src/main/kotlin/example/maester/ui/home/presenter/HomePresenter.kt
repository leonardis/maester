package example.maester.ui.home.presenter

import example.maester.api.Endpoints
import example.maester.base.mvp.BasePresenter
import example.maester.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomePresenter @Inject constructor(disposable: CompositeDisposable, scheduler: SchedulerProvider) : BasePresenter<MoviesView>(disposable, scheduler) {

    //TODO Methods from presenter
}
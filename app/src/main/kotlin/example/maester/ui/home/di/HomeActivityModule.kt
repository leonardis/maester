package example.maester.ui.home.di

import example.maester.base.di.ActivityScope
import example.maester.api.Endpoints
import dagger.Module
import dagger.Provides
import example.maester.ui.home.presenter.HomePresenter
import example.maester.utils.AppSchedulerProvider
import io.reactivex.disposables.CompositeDisposable

@Module
class HomeActivityModule {

    @Provides
    @ActivityScope
    internal fun providesHomePresenter(disposable: CompositeDisposable, scheduler: AppSchedulerProvider): HomePresenter =
            HomePresenter(disposable, scheduler)
}

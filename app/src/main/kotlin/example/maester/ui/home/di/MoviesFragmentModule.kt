package example.maester.ui.home.di

import example.maester.base.di.ActivityScope
import example.maester.api.Endpoints
import dagger.Module
import dagger.Provides
import example.maester.ui.home.presenter.HomePresenter
import example.maester.ui.home.presenter.MoviesPresenter
import example.maester.utils.AppSchedulerProvider
import io.reactivex.disposables.CompositeDisposable

@Module
class MoviesFragmentModule {

    @Provides
    @ActivityScope
    internal fun providesMoviesPresenter(api: Endpoints, disposable: CompositeDisposable,
                                         scheduler: AppSchedulerProvider): MoviesPresenter =
            MoviesPresenter(api, disposable, scheduler)
}

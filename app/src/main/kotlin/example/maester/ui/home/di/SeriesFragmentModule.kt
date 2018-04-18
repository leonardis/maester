package example.maester.ui.home.di

import example.maester.base.di.ActivityScope
import example.maester.api.Endpoints
import dagger.Module
import dagger.Provides
import example.maester.ui.home.presenter.HomePresenter
import example.maester.ui.home.presenter.MoviesPresenter
import example.maester.ui.home.presenter.SeriesPresenter
import example.maester.utils.AppSchedulerProvider
import io.reactivex.disposables.CompositeDisposable

@Module
class SeriesFragmentModule {

    @Provides
    @ActivityScope
    internal fun providesSeriesPresenter(api: Endpoints, disposable: CompositeDisposable,
                                         scheduler: AppSchedulerProvider): SeriesPresenter =
            SeriesPresenter(api, disposable, scheduler)
}

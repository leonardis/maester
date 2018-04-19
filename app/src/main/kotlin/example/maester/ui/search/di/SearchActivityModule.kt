package example.maester.ui.search.di

import example.maester.base.di.ActivityScope
import example.maester.api.Endpoints
import dagger.Module
import dagger.Provides
import example.maester.ui.search.presenter.SearchPresenter
import example.maester.utils.AppSchedulerProvider
import io.reactivex.disposables.CompositeDisposable

@Module
class SearchActivityModule {
    @Provides
    @ActivityScope
    internal fun providesSearchPresenter(api: Endpoints, disposable: CompositeDisposable,
                                         scheduler: AppSchedulerProvider): SearchPresenter =
            SearchPresenter(api, disposable, scheduler)
}

package example.maester.ui.detail.di

import example.maester.base.di.ActivityScope
import example.maester.api.Endpoints
import dagger.Module
import dagger.Provides
import example.maester.ui.detail.presenter.DetailPresenter
import example.maester.utils.AppSchedulerProvider
import io.reactivex.disposables.CompositeDisposable

@Module
class DetailActivityModule {
    @Provides
    @ActivityScope
    internal fun providesDetailPresenter(api: Endpoints, disposable: CompositeDisposable,
                                         scheduler: AppSchedulerProvider): DetailPresenter =
            DetailPresenter(api, disposable, scheduler)
}

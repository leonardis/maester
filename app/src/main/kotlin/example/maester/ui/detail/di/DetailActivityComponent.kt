package example.maester.ui.detail.di

import dagger.Component
import example.maester.base.di.ActivityScope
import example.maester.base.di.component.AppComponent
import example.maester.ui.detail.DetailActivity

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(DetailActivityModule::class))
interface DetailActivityComponent {

    fun inject(detailActivity: DetailActivity)
}

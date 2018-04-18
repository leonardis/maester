package example.maester.ui.home.di

import dagger.Component
import example.maester.base.di.ActivityScope
import example.maester.base.di.component.AppComponent
import example.maester.ui.home.fragments.SeriesFragment

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(SeriesFragmentModule::class))
interface SeriesFragmentComponent {

    fun inject(seriesFragment: SeriesFragment)
}

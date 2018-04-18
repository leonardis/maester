package example.maester.ui.home.di

import dagger.Component
import example.maester.base.di.ActivityScope
import example.maester.base.di.component.AppComponent
import example.maester.ui.home.fragments.MoviesFragment

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(MoviesFragmentModule::class))
interface MoviesFragmentComponent {

    fun inject(moviesFragment: MoviesFragment)
}

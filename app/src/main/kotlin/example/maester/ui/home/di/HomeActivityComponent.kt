package example.maester.ui.home.di

import dagger.Component
import example.maester.base.di.ActivityScope
import example.maester.base.di.component.AppComponent
import example.maester.ui.home.HomeActivity

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(HomeActivityModule::class))
interface HomeActivityComponent {

    fun inject(homeActivity: HomeActivity)
}

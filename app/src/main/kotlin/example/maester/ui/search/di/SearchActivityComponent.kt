package example.maester.ui.search.di

import dagger.Component
import example.maester.base.di.ActivityScope
import example.maester.base.di.component.AppComponent
import example.maester.ui.search.SearchActivity

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(SearchActivityModule::class))
interface SearchActivityComponent {

    fun inject(searchActivity: SearchActivity)
}

package example.maester.ui.home

import android.os.Bundle
import example.maester.R
import example.maester.base.BaseActivity
import example.maester.base.adapter.PageAdapter
import example.maester.base.mvp.BaseView
import example.maester.ui.home.di.DaggerHomeActivityComponent
import example.maester.ui.home.di.HomeActivityModule
import example.maester.ui.home.fragments.MoviesFragment
import example.maester.ui.home.fragments.SeriesFragment
import example.maester.utils.SharedPreferencesHelper
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), BaseView {

    @Inject
    lateinit var spHelper: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setupPageAdapter()
    }

    override fun onActivityInject() {
        DaggerHomeActivityComponent.builder().appComponent(getAppcomponent())
                .homeActivityModule(HomeActivityModule())
                .build()
                .inject(this)
    }

    private fun setupPageAdapter() {
        val pageAdapter = PageAdapter(supportFragmentManager)

        pageAdapter.add(MoviesFragment.newInstance(0, this@HomeActivity), "Peliculas")
        pageAdapter.add(SeriesFragment.newInstance(1, this@HomeActivity), "Series")

        viewPager.adapter = pageAdapter
        tabs.setupWithViewPager(viewPager)
    }
}

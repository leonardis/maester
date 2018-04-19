package example.maester.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import example.maester.R
import example.maester.base.BaseActivity
import example.maester.base.adapter.PageAdapter
import example.maester.base.mvp.BaseView
import example.maester.ui.home.di.DaggerHomeActivityComponent
import example.maester.ui.home.di.HomeActivityModule
import example.maester.ui.home.fragments.MoviesFragment
import example.maester.ui.home.fragments.SeriesFragment
import example.maester.ui.search.SearchActivity
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_toolbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class HomeActivity : BaseActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setupPageAdapter()

        searchButton.setOnClickListener {
            EventBus.getDefault().post(it)
        }
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

    @Subscribe
    fun onSearchClicked(view: View) {
        val intent = Intent(this@HomeActivity, SearchActivity::class.java)
        intent.putExtra("page", viewPager.currentItem)
        startActivity(intent)
    }
}

package example.maester.ui.search

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import com.burakeregar.easiestgenericrecycleradapter.base.GenericAdapterBuilder
import com.burakeregar.easiestgenericrecycleradapter.base.GenericRecyclerAdapter
import com.jakewharton.rxbinding2.widget.RxTextView
import example.maester.R
import example.maester.base.BaseActivity
import example.maester.models.MoviesResult
import example.maester.models.SeriesResult
import example.maester.ui.detail.DetailActivity
import example.maester.ui.search.di.DaggerSearchActivityComponent
import example.maester.ui.search.di.SearchActivityModule
import example.maester.ui.search.presenter.SearchPresenter
import example.maester.ui.search.presenter.SearchView
import example.maester.ui.search.viewholders.SearchMovieViewHolder
import example.maester.ui.search.viewholders.SearchSeriesViewHolder
import example.maester.utils.DEBUG_TAG
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.search_toolbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchActivity : BaseActivity(), SearchView {

    @Inject
    lateinit var presenter: SearchPresenter

    private lateinit var searchAdapter: GenericRecyclerAdapter

    private var page: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        page = intent.getIntExtra("page", 0)
        when (page) {
            0 -> searchField.hint = "Busca una pelicula"
            1 -> searchField.hint = "Busca una serie"
        }

        initAdapter()

        RxTextView.textChangeEvents(searchField)
                .debounce(200, TimeUnit.MILLISECONDS)
                .filter({ query -> query.text().isNotEmpty() })
                .subscribe({ query ->
                    when (page) {
                        0 -> presenter.searchMovie(query.text().toString())
                        1 -> presenter.searchSeries(query.text().toString())
                    }})

        backButton.setOnClickListener {
            EventBus.getDefault().post(it)
        }
    }

    override fun onActivityInject() {
        DaggerSearchActivityComponent.builder().appComponent(getAppcomponent())
                .searchActivityModule(SearchActivityModule())
                .build()
                .inject(this)

        presenter.attachView(this)

    }

    override fun onMovieResponse(list: List<MoviesResult>) {
        searchAdapter.setList(list)
    }

    override fun onSeriesResponse(list: List<SeriesResult>) {
        searchAdapter.setList(list)
    }

    override fun showProgress() {
        Log.d(DEBUG_TAG, "SHOW PROGRESS")
    }

    override fun hideProgress() {
        Log.d(DEBUG_TAG, "HIDE PROGRESS")
    }

    override fun noResult() {
        toast("We couldn't find any result.")
    }

    private fun initAdapter() {
        when (page) {
            0 -> searchAdapter = GenericAdapterBuilder().addModel(
                        R.layout.simple_movie_grid_item,
                        SearchMovieViewHolder::class.java,
                        MoviesResult::class.java)
                        .execute()

            1 -> searchAdapter = GenericAdapterBuilder().addModel(
                        R.layout.simple_movie_grid_item,
                        SearchSeriesViewHolder::class.java,
                        SeriesResult::class.java)
                        .execute()
        }

        with(searchRv) {
            layoutManager = GridLayoutManager(this@SearchActivity, 2)
            adapter = searchAdapter
        }
    }

    @Subscribe
    fun onSearchClicked(view: View) {
        finish()
    }

    @Subscribe
    fun onRowMovieClicked(item: MoviesResult) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("id", item.id)
        intent.putExtra("page", 0)
        startActivity(intent)
    }

    @Subscribe
    fun onRowSeriesClicked(item: SeriesResult) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("id", item.id)
        intent.putExtra("page", 1)
        startActivity(intent)
    }
}

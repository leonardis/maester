package example.maester.ui.home.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.burakeregar.easiestgenericrecycleradapter.base.GenericAdapterBuilder
import com.burakeregar.easiestgenericrecycleradapter.base.GenericRecyclerAdapter
import example.maester.R
import example.maester.base.BaseFragment
import example.maester.models.MoviesResult
import example.maester.models.SeriesResult
import example.maester.ui.detail.DetailActivity
import example.maester.ui.home.di.DaggerSeriesFragmentComponent
import example.maester.ui.home.di.SeriesFragmentModule
import example.maester.ui.home.presenter.SeriesPresenter
import example.maester.ui.home.presenter.SeriesView
import example.maester.ui.home.viewholders.SeriesViewHolder
import example.maester.utils.DEBUG_TAG
import kotlinx.android.synthetic.main.fragment_movies.view.*
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class SeriesFragment() : BaseFragment(), SeriesView {

    private lateinit var providedContext: Context

    @SuppressLint("ValidFragment")
    constructor(context: Context) : this() {
        providedContext = context
    }

    @Inject
    lateinit var presenter: SeriesPresenter

    private lateinit var popularAdapter: GenericRecyclerAdapter
    private lateinit var topRatedAdapter: GenericRecyclerAdapter
    private lateinit var upcomingAdapter: GenericRecyclerAdapter

    private lateinit var popularRv: RecyclerView
    private lateinit var ratedRv: RecyclerView
    private lateinit var upcomingRv: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movies, container, false)

        popularRv = view.findViewById(R.id.popularMovieRv)
        ratedRv = view.findViewById(R.id.valuedMovieRv)
        upcomingRv = view.findViewById(R.id.upcomingMovieRv)

        view.nextTv.text = "Que pasan hoy"

        initAdapter()
        presenter.getPopulars()
        presenter.getTopRated()
        presenter.getAiringToday()

        return view
    }

    companion object {
        val PAGE_NUM = "PAGE_NUM"
        fun newInstance(page: Int, context: Context): SeriesFragment {
            val fragment = SeriesFragment(context)
            val args = Bundle()
            args.putInt(PAGE_NUM, page)
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onFragmentInject() {
        DaggerSeriesFragmentComponent.builder().appComponent(getAppcomponent())
                .seriesFragmentModule(SeriesFragmentModule())
                .build()
                .inject(this)

        presenter.attachView(this)
    }

    override fun onResponse(list: List<SeriesResult>, type: Int) {
        when (type) {
            1 -> popularAdapter.setList(list)
            2 -> topRatedAdapter.setList(list)
            3 -> upcomingAdapter.setList(list)
        }
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
        popularAdapter = GenericAdapterBuilder().addModel(
                R.layout.movie_item,
                SeriesViewHolder::class.java,
                SeriesResult::class.java)
                .execute()

        topRatedAdapter = GenericAdapterBuilder().addModel(
                R.layout.movie_item,
                SeriesViewHolder::class.java,
                SeriesResult::class.java)
                .execute()

        upcomingAdapter = GenericAdapterBuilder().addModel(
                R.layout.movie_item,
                SeriesViewHolder::class.java,
                SeriesResult::class.java)
                .execute()

        with(popularRv) {
            layoutManager = LinearLayoutManager(providedContext, LinearLayoutManager
                    .HORIZONTAL, false)
            adapter = popularAdapter
        }

        with(ratedRv) {
            layoutManager = LinearLayoutManager(providedContext, LinearLayoutManager.HORIZONTAL, false)
            adapter = topRatedAdapter
        }

        with(upcomingRv) {
            layoutManager = LinearLayoutManager(providedContext, LinearLayoutManager.HORIZONTAL, false)
            adapter = upcomingAdapter
        }
    }

    @Subscribe
    fun onRowClicked(item: MoviesResult) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("id", item.id)
        context!!.startActivity(intent)
    }
}
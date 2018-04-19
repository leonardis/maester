package example.maester.ui.detail

import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.burakeregar.easiestgenericrecycleradapter.base.GenericAdapterBuilder
import com.burakeregar.easiestgenericrecycleradapter.base.GenericRecyclerAdapter
import example.maester.R
import example.maester.base.BaseActivity
import example.maester.models.*
import example.maester.ui.detail.di.DaggerDetailActivityComponent
import example.maester.ui.detail.di.DetailActivityModule
import example.maester.ui.detail.presenter.DetailPresenter
import example.maester.ui.detail.presenter.DetailView
import example.maester.ui.detail.viewholders.GenreViewholder
import example.maester.ui.detail.viewholders.SeasonsViewHolder
import example.maester.ui.detail.viewholders.SimpleMovieViewHolder
import example.maester.utils.BASE_IMAGE_URL
import example.maester.utils.DEBUG_TAG
import example.maester.utils.extensions.minToHours
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.toast
import javax.inject.Inject

class DetailActivity : BaseActivity(), DetailView {

    @Inject
    lateinit var presenter: DetailPresenter

    private lateinit var similarMovieAdapter: GenericRecyclerAdapter
    private lateinit var genreAdapter: GenericRecyclerAdapter

    private var page: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id: String = intent.getIntExtra("id", 0).toString()
        page = intent.getIntExtra("page", 0)

        initAdapter()
        when (page) {
            0 -> {
                presenter.getMovieDetail(id)
                presenter.getSimilarMovies(id)
            }
            1 -> {
                subListText.text = "Temporadas"
                presenter.getSerieDetail(id)
            }
        }

        backButton.setOnClickListener {
            EventBus.getDefault().post(it)
        }
    }

    override fun onActivityInject() {
        DaggerDetailActivityComponent.builder().appComponent(getAppcomponent())
                .detailActivityModule(DetailActivityModule())
                .build()
                .inject(this)

        presenter.attachView(this)
    }

    override fun onMovieResponse(movie: Movie) {
        val uri = Uri.parse(BASE_IMAGE_URL + movie.backdropPath)
        movieImageView.setImageURI(uri)
        movieTitle.text = movie.title
        movieYearRelease.text = movie.releaseDate.substring(0, 4)
        movieVotes.text = "${movie.voteCount} votos"
        movieRate.text = movie.voteAverage.toString()
        movieDuration.text = movie.runtime.minToHours
        movieDescription.text = movie.overview
        genreAdapter.setList(movie.genres)
    }

    override fun onSerieResponse(series: Serie) {
        val uri = Uri.parse(BASE_IMAGE_URL + series.backdropPath)
        movieImageView.setImageURI(uri)
        movieTitle.text = series.name
        movieYearRelease.text = series.firstAirDate.substring(0, 4)
        movieVotes.text = "${series.voteCount} votos"
        movieRate.text = series.voteAverage.toString()
        movieDuration.text = "${series.numberOfSeasons} Temp - ${series.numberOfEpisodes} Ep"
        movieDescription.text = series.overview
        genreAdapter.setList(series.genres)
        similarMovieAdapter.setList(series.seasons)
    }

    override fun onSimilarMovieResponse(list: List<MoviesResult>) {
        similarMovieAdapter.setList(list)
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
            0 -> similarMovieAdapter = GenericAdapterBuilder().addModel(
                        R.layout.simple_movie_item,
                        SimpleMovieViewHolder::class.java,
                        MoviesResult::class.java)
                        .execute()
            1 -> similarMovieAdapter = GenericAdapterBuilder().addModel(
                        R.layout.simple_movie_item,
                        SeasonsViewHolder::class.java,
                        Season::class.java)
                        .execute()
        }

        genreAdapter = GenericAdapterBuilder().addModel(
                R.layout.simple_item,
                GenreViewholder::class.java,
                Genre::class.java)
                .execute()

        with(movieSimilarRv) {
            layoutManager = LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = similarMovieAdapter
        }

        with(movieGenreRv) {
            layoutManager = LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = genreAdapter
        }
    }

    @Subscribe
    fun buttonClicked(view: View) {
        finish()
    }
}

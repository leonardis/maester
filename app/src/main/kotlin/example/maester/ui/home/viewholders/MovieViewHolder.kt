package example.maester.ui.home.viewholders

import android.net.Uri
import android.view.View
import com.burakeregar.easiestgenericrecycleradapter.base.GenericViewHolder
import example.maester.base.event.RowClickEvent
import example.maester.models.MoviesResult
import example.maester.utils.BASE_IMAGE_URL
import kotlinx.android.synthetic.main.movie_item.view.*
import org.greenrobot.eventbus.EventBus

class MovieViewHolder(itemView: View?) : GenericViewHolder<Any>(itemView) {
    private lateinit var mItem: MoviesResult

    override fun bindData(p0: Any?) {
        mItem = p0 as MoviesResult
        with(itemView) {
            val uri = Uri.parse(BASE_IMAGE_URL + mItem.posterPath)
            movieImageView?.setImageURI(uri)
            movieTitle?.text = mItem.title
            movieDate?.text = mItem.releaseDate.substring(0, 4)
            movieVotes?.text = mItem.voteAverage.toString()
            movieItem.setOnClickListener {
                EventBus.getDefault().post(RowClickEvent(mItem))
            }
        }
    }
}
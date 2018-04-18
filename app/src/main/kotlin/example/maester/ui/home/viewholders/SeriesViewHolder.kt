package example.maester.ui.home.viewholders

import android.net.Uri
import android.view.View
import com.burakeregar.easiestgenericrecycleradapter.base.GenericViewHolder
import example.maester.base.event.RowClickEvent
import example.maester.models.MoviesResult
import example.maester.models.SeriesResult
import example.maester.utils.BASE_IMAGE_URL
import kotlinx.android.synthetic.main.movie_item.view.*
import org.greenrobot.eventbus.EventBus

class SeriesViewHolder(itemView: View?) : GenericViewHolder<Any>(itemView) {
    private lateinit var mItem: SeriesResult

    override fun bindData(p0: Any?) {
        mItem = p0 as SeriesResult
        with(itemView) {
            val uri = Uri.parse(BASE_IMAGE_URL + mItem.posterPath)
            movieImageView?.setImageURI(uri)
            movieTitle?.text = mItem.name
            movieDate?.text = if (mItem.firstAirDate.isNotEmpty()) mItem.firstAirDate.substring(0, 4)
            else { mItem.firstAirDate }
            movieVotes?.text = mItem.voteAverage.toString()
            movieItem.setOnClickListener {
                //EventBus.getDefault().post(RowClickEvent(mItem))
            }
        }
    }
}
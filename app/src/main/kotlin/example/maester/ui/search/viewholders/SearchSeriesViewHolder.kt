package example.maester.ui.search.viewholders

import android.net.Uri
import android.view.View
import com.burakeregar.easiestgenericrecycleradapter.base.GenericViewHolder
import example.maester.models.SeriesResult
import example.maester.utils.BASE_IMAGE_URL
import kotlinx.android.synthetic.main.simple_movie_grid_item.view.*
import org.greenrobot.eventbus.EventBus

class SearchSeriesViewHolder(itemView: View?) : GenericViewHolder<Any>(itemView) {
    private lateinit var mItem: SeriesResult

    override fun bindData(p0: Any?) {
        mItem = p0 as SeriesResult
        with(itemView) {
            val uri = Uri.parse(BASE_IMAGE_URL + mItem.posterPath)
            movieImageView?.setImageURI(uri)
            movieItem.setOnClickListener {
                EventBus.getDefault().post(mItem)
            }
        }
    }
}
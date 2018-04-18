package example.maester.ui.detail.viewholders

import android.net.Uri
import android.view.View
import com.burakeregar.easiestgenericrecycleradapter.base.GenericViewHolder
import example.maester.models.MoviesResult
import example.maester.utils.BASE_IMAGE_URL
import kotlinx.android.synthetic.main.simple_movie_item.view.*
import org.greenrobot.eventbus.EventBus

class SimpleMovieViewHolder(itemView: View?) : GenericViewHolder<Any>(itemView) {
    private lateinit var mItem: MoviesResult

    override fun bindData(p0: Any?) {
        mItem = p0 as MoviesResult
        with(itemView) {
            val uri = Uri.parse(BASE_IMAGE_URL + mItem.posterPath)
            movieImageView?.setImageURI(uri)
            movieTitle?.text = mItem.title
            movieItem.setOnClickListener {
                EventBus.getDefault().post(mItem)
            }
        }
    }
}
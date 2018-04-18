package example.maester.ui.detail.viewholders

import android.view.View
import com.burakeregar.easiestgenericrecycleradapter.base.GenericViewHolder
import example.maester.models.Genre
import kotlinx.android.synthetic.main.simple_item.view.*

class GenreViewholder(itemView: View?) : GenericViewHolder<Any>(itemView) {
    private lateinit var item: Genre

    override fun bindData(p0: Any?) {
        item = p0 as Genre
        with(itemView) {
            simpleTitle.text = item.name
        }
    }
}
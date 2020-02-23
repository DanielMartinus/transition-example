package nl.dionsegijn.transitionexample.fragments.item

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_image_row.*
import nl.dionsegijn.transitionexample.R

class ImageRowItem(
    val id: String, val imageUrl: String,
    val onOpenImage: (imageView: View, imageUrl: String, transitionId: String) -> Unit
): Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val context = viewHolder.itemView.context

        val density = (8f * context.resources.displayMetrics.density).toInt()
        val requestOptions = RequestOptions()
            .transform(CenterCrop(), RoundedCorners(density))

        Glide.with(context)
            .load(imageUrl)
            .apply(requestOptions)
            .into(viewHolder.imageView)
        viewHolder.imageView.transitionName = "shared_element_container_$id"
        viewHolder.imageView.setOnClickListener { onOpenImage(viewHolder.imageView, imageUrl, id) }
    }

    override fun getLayout(): Int = R.layout.item_image_row
}

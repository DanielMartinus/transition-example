package nl.dionsegijn.transitionexample.fragments


import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.doOnPreDraw
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.fragment_second.*
import nl.dionsegijn.transitionexample.R

class SecondFragment : Fragment() {

    companion object {
        internal const val ARG_IMAGE_URL = "ARG_IMAGE_URL"
        internal const val ARG_TRANSITION_ID = "ARG_TRANSITION_ID"
        fun create(imageUrl: String, transitionId: String) = SecondFragment().apply {
            arguments = bundleOf(
                ARG_IMAGE_URL to imageUrl,
                ARG_TRANSITION_ID to transitionId
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()

        val transitionId = requireNotNull(arguments?.getString(ARG_TRANSITION_ID))
        Log.d("transition","shared_element_container_$transitionId")
        imageView.transitionName = "shared_element_container_$transitionId"

        val imageUrl = requireNotNull(arguments?.getString(ARG_IMAGE_URL))
            // Parent has been drawn. Start transitioning!
        Glide.with(this)
            .load(imageUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }
            })
            .into(imageView)

    }

}

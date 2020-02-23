package nl.dionsegijn.transitionexample.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_first.*
import nl.dionsegijn.transitionexample.R

class FirstFragment : Fragment() {

    private val imageUrl = "https://images.unsplash.com/photo-1582343129400-d9883f401c36"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val density = (8f * view.context.resources.displayMetrics.density).toInt()
        val requestOptions = RequestOptions()
            .transform(CenterCrop(), RoundedCorners(density))

        Glide.with(this)
            .load(imageUrl)
            .apply(requestOptions)
            .into(imageView)

        imageView.setOnClickListener {
            (activity as TransitionNavigator).startTransition(imageView, imageUrl)
        }
    }
}

package nl.dionsegijn.transitionexample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_first.*
import nl.dionsegijn.transitionexample.R
import nl.dionsegijn.transitionexample.fragments.item.ImageRowItem

class FirstFragment : Fragment() {

    private val imageUrl = "https://images.unsplash.com/photo-1582343129400-d9883f401c36"

    private val imageAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvImages.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = imageAdapter
        }
        imageAdapter.update(listOf(
            ImageRowItem("1", imageUrl) { imageView, url, id -> onOpenImage(imageView, url, id) }
        ))
    }

    private fun onOpenImage(view: View, imageUrl: String, transitionId: String) {
        (activity as TransitionNavigator).startTransition(view, imageUrl, transitionId)
    }
}

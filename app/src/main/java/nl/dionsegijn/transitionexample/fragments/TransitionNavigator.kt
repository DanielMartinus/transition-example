package nl.dionsegijn.transitionexample.fragments

import android.view.View
import android.widget.ImageView

interface TransitionNavigator {
    fun startTransition(view: View, imageUrl: String)
}

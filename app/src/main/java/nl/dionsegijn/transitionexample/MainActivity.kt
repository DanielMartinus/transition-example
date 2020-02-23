package nl.dionsegijn.transitionexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.transition.MaterialContainerTransform
import nl.dionsegijn.transitionexample.fragments.FirstFragment
import nl.dionsegijn.transitionexample.fragments.SecondFragment
import nl.dionsegijn.transitionexample.fragments.TransitionNavigator

class MainActivity : AppCompatActivity(), TransitionNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFirstFragment()
    }

    private fun initFirstFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, FirstFragment(), "FirstFragment")
            .commit()
    }

    override fun startTransition(view: View) {
        val tag = "SecondFragment"
        val fragment = SecondFragment()
        fragment.sharedElementEnterTransition = MaterialContainerTransform(applicationContext)

        supportFragmentManager
            .beginTransaction()
            .addSharedElement(view,  "shared_element_container")
            .replace(R.id.container, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }

}

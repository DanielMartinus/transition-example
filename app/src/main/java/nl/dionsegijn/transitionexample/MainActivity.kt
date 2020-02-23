package nl.dionsegijn.transitionexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

    override fun startTransition(view: View, imageUrl: String, transitionId: String) {
        val tag = "SecondFragment"
        val fragment = SecondFragment.create(imageUrl, transitionId)
        fragment.sharedElementEnterTransition = MaterialContainerTransform(applicationContext)
        fragment.sharedElementReturnTransition = MaterialContainerTransform(applicationContext)

        Log.d("transition","shared_element_container_$transitionId")

        supportFragmentManager
            .beginTransaction()
            .setReorderingAllowed(true)
            .addSharedElement(view,  "shared_element_container_$transitionId")
            .replace(R.id.container, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }

}

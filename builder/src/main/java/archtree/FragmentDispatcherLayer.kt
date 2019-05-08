package archtree

import android.os.Bundle

interface FragmentDispatcherLayer {

    fun onCreateFragment(containerId: Int, state: Int, bundle: Bundle?): Boolean
}
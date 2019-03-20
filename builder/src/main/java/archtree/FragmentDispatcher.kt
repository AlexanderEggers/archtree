package archtree

import android.os.Bundle

interface FragmentDispatcher {

    fun showFragment(containerId: Int, state: Enum<*>, bundle: Bundle?): Boolean
    fun showFragment(containerId: Int, state: Int, bundle: Bundle?): Boolean
}
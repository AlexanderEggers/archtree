package archtree.viewmodel

import androidx.lifecycle.ViewModel
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

abstract class BaseViewModel : ViewModel() {

    var isInitialised: Boolean = false
        private set

    @JvmOverloads
    fun init(forceInit: Boolean = false, resourceBundle: Bundle? = null, customBundle: Bundle? = null, savedInstanceBundle: Bundle? = null) {
        if (!isInitialised || forceInit) {
            isInitialised = true
            onInit(resourceBundle, customBundle, savedInstanceBundle)
        }
    }

    /**
     *  Main method for the viewmodel and entry point from the ArchTreeActivity or ArchTreeFragment.
     *  This method provides a resource bundle and a saved instance bundle. The resource bundle
     *  is provided by the attached fragment or activity. The saved instance bundle is provided by
     *  the state of the activity or fragment - this bundle should be used as if you would work
     *  within e.g. an activity.
     *
     *  @param resourceBundle Bundle object that is provided by the attached activity or fragment
     *  @param customBundle Custom bundle object
     *  @param savedInstanceBundle Bundle object that is provided by the state of the attached
     *  activity or fragment
     *
     *  @since 1.0.0
     */
    protected open fun onInit(resourceBundle: Bundle?, customBundle: Bundle?, savedInstanceBundle: Bundle?) {
        //do nothing by default
    }

    open fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        return false
    }

    open fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return false
    }

    open fun onOptionsItemSelected(item: MenuItem): Boolean {
        return false
    }

    open fun onBackPressed(): Boolean {
        return false
    }

    open fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray): Boolean {
        return false
    }

    open fun onConfigurationChanged(newConfig: Configuration): Boolean {
        return false
    }

    open fun onNewIntent(intent: Intent?): Boolean {
        return false
    }

    open fun onSaveInstanceState(outState: Bundle) {
        //do nothing by default
    }

    open fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        //do nothing by default
    }
}

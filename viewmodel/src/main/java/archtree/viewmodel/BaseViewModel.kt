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
    fun init(forceInit: Boolean = false, resourceBundle: Bundle = Bundle(), savedInstanceBundle: Bundle? = null) {
        if (!isInitialised || forceInit) {
            isInitialised = true
            onInit(resourceBundle, savedInstanceBundle)
        }
    }

    protected open fun onInit(resourceBundle: Bundle?, savedInstanceBundle: Bundle?) {
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

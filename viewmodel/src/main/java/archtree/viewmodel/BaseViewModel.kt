package archtree.viewmodel

import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.os.Bundle
import android.support.v7.preference.Preference
import android.view.MenuItem

abstract class BaseViewModel : ViewModel() {

    var isInitialised: Boolean = false
        private set

    @JvmOverloads
    fun init(forceInit: Boolean = false, bundle: Bundle? = null) {
        if (!isInitialised || forceInit) {
            isInitialised = true
            onInit(bundle)
        }
    }

    protected open fun onInit(bundle: Bundle?) {
        //do nothing by default
    }

    open fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //do nothing by default
    }

    open fun onOptionsItemSelected(item: MenuItem?) {
        //do nothing by default
    }

    open fun onBackPressed(): Boolean {
        return true
    }

    open fun onPreferenceTreeClick(preference: Preference?): Boolean {
        return false
    }
}

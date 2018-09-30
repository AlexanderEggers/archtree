package archtree.viewmodel

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.annotation.IntRange
import android.support.v4.app.ActivityCompat
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

    fun requestPermissions(activity: Activity, permissions: Array<String>, @IntRange(from = 0) requestCode: Int) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
    }

    protected open fun onInit(bundle: Bundle?) {
        //do nothing by default
    }

    open fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        return false
    }

    open fun onOptionsItemSelected(item: MenuItem?) {
        //do nothing by default
    }

    open fun onBackPressed(): Boolean {
        return false
    }

    open fun onPreferenceTreeClick(preference: Preference?): Boolean {
        return false
    }

    open fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray): Boolean {
        return false
    }

    open fun onConfigurationChanged(newConfig: Configuration?): Boolean {
        return false
    }
}

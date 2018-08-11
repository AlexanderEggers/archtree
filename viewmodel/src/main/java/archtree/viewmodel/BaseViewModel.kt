package archtree.viewmodel

import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.os.Bundle
import android.support.annotation.CallSuper
import android.view.MenuItem

abstract class BaseViewModel : ViewModel() {

    private var hasInitialised: Boolean = false

    @JvmOverloads
    fun init(forceInit: Boolean = false, bundle: Bundle? = null) {
        if (!hasInitialised || forceInit) {
            hasInitialised = true
            onInit(bundle)
        }
    }

    @CallSuper
    protected open fun onInit(bundle: Bundle?) {
        //do nothing by default
    }

    @CallSuper
    open fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?, bundle: Bundle?) {
        //do nothing by default
    }

    @CallSuper
    open fun onOptionsItemSelected(item: MenuItem) {
        //do nothing by default
    }
}

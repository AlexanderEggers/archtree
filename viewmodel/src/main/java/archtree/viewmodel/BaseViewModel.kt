package archtree.viewmodel

import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.os.Bundle
import android.support.annotation.CallSuper

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

    }

    fun sendActivityResult(requestCode: Int, resultCode: Int, data: Intent?, bundle: Bundle?) {
        onActivityResult(requestCode, resultCode, data, bundle)
    }

    @CallSuper
    protected open fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?, bundle: Bundle?) {

    }
}

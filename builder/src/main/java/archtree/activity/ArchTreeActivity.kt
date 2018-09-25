package archtree.activity

import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import archtree.fragment.ArchTreeFragmentCommunicator
import archtree.viewmodel.BaseViewModel
import autotarget.util.HasFragmentFlow
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class ArchTreeActivity<ViewModel : BaseViewModel> : AppCompatActivity(),
        HasSupportFragmentInjector, HasFragmentFlow, HasActivityBuilder<ViewModel> {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var activityResource: ActivityResource<ViewModel>? = null

    override fun onResume() {
        super.onResume()
        activityResource?.getLayer()?.onResume(getViewModel(), getBundle())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityBuilder = ActivityBuilder<ViewModel>()
        activityResource = provideActivityResource(activityBuilder)

        val view = activityResource?.onCreateView(layoutInflater, null)
        setContentView(view)

        if (activityResource?.viewModelClass != null) {
            activityResource?.onCreateViewModel(this, viewModelFactory)
        }

        if (activityResource?.hideSupportBar == true) {
            supportActionBar?.hide()
        } else {
            val toolbarViewId = getActivityResource()?.toolbarViewId
            val toolbarTitle = getActivityResource()?.toolbarTitle

            if (toolbarViewId != null) {
                setSupportActionBar(findViewById(toolbarViewId))

                val displayHomeAsUpEnabled = getActivityResource()?.displayHomeAsUpEnabled ?: false
                supportActionBar?.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)

                if (toolbarTitle != null) {
                    supportActionBar?.title = toolbarTitle
                }

                val toolbarIcon = getActivityResource()?.toolbarIcon
                if (toolbarIcon != null) {
                    supportActionBar?.setIcon(toolbarIcon)
                }
            } else if (toolbarTitle != null) {
                title = toolbarTitle
            }
        }

        val systemUiVisibility = getActivityResource()?.systemUiVisibility
        if (systemUiVisibility != null && systemUiVisibility != 0) {
            window.decorView.systemUiVisibility = systemUiVisibility
        }

        activityResource?.getLayer()?.onCreate(getViewModel(), getBundle())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuId = activityResource?.menuId
        if (menuId != null) {
            val inflater = menuInflater
            inflater.inflate(menuId, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        var hasHandledBackPressed = false
        supportFragmentManager.fragments.forEach { fragment ->
            if (fragment is ArchTreeFragmentCommunicator? && fragment?.isVisible == true) {
                val fragmentHasHandledBackPressed = fragment.onBackPressed()
                if (!hasHandledBackPressed) {
                    hasHandledBackPressed = fragmentHasHandledBackPressed

                    if (fragmentHasHandledBackPressed) {
                        return //has been handled by fragment
                    }
                }
            }
        }

        if (hasHandledBackPressed) {
            val shouldRunDefaultBackPressed = getViewModel()?.onBackPressed() ?: false
            if (!shouldRunDefaultBackPressed) onDefaultBackPressed()
        }
    }

    open fun onDefaultBackPressed() {
        super.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var hasHandledActivityResult = false
        supportFragmentManager.fragments.forEach { fragment ->
            if (fragment is ArchTreeFragmentCommunicator? && fragment?.isVisible == true) {
                val fragmentHasHandledActivityResult = fragment.onFragmentActivityResult(requestCode, resultCode, data)
                if (!hasHandledActivityResult) {
                    hasHandledActivityResult = fragmentHasHandledActivityResult

                    if (fragmentHasHandledActivityResult) {
                        return //has been handled by fragment
                    }
                }
            }
        }

        if (!hasHandledActivityResult) {
            val shouldRunDefaultActivityResult = getViewModel()?.onActivityResult(requestCode, resultCode, data)
                    ?: false
            if (!shouldRunDefaultActivityResult) onDefaultActivityResult(requestCode, resultCode, data)
        }
    }

    open fun onDefaultActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        var hasHandledRequestPermissionResult = false
        supportFragmentManager.fragments.forEach { fragment ->
            if (fragment is ArchTreeFragmentCommunicator? && fragment?.isVisible == true) {
                val fragmentHasHandledRequestPermissionResult = fragment.onFragmentRequestPermissionsResult(requestCode, permissions, grantResults)
                if (!hasHandledRequestPermissionResult) {
                    hasHandledRequestPermissionResult = fragmentHasHandledRequestPermissionResult

                    if (fragmentHasHandledRequestPermissionResult) {
                        return //has been handled by fragment
                    }
                }
            }
        }

        if (!hasHandledRequestPermissionResult) {
            val shouldRunDefaultRequestPermissionsResult = getViewModel()?.onRequestPermissionsResult(requestCode, permissions, grantResults)
                    ?: false
            if (!shouldRunDefaultRequestPermissionsResult) onDefaultRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    open fun onDefaultRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
            else -> getViewModel()?.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        getActivityResource()?.getLayer()?.onStart(getViewModel())
    }

    override fun onStop() {
        super.onStop()
        getActivityResource()?.getLayer()?.onStop(getViewModel())
    }

    override fun onDestroy() {
        super.onDestroy()
        getActivityResource()?.getLayer()?.onDestroy(getViewModel())
    }

    override fun onShowNextFragment(containerId: Int, state: Int, addToBackStack: Boolean, clearBackStack: Boolean, bundle: Bundle?): Boolean {
        return activityResource?.fragmentFlow?.onShowNextFragment(containerId, state, addToBackStack, clearBackStack, bundle)
                ?: false
    }

    open fun getActivityResource(): ActivityResource<ViewModel>? {
        return activityResource
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    open fun getView(): View? {
        return window.decorView.findViewById(android.R.id.content)
    }

    open fun getViewModel(): ViewModel? {
        return activityResource?.viewModel
    }

    open fun getBinding(): ViewDataBinding? {
        return activityResource?.binding
    }

    private fun getBundle(): Bundle? {
        return activityResource?.bundle
    }
}
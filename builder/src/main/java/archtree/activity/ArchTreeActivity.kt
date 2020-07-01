package archtree.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import archtree.fragment.ArchTreeFragment
import archtree.viewmodel.BaseViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class ArchTreeActivity : AppCompatActivity(),
        HasAndroidInjector, HasActivityBuilder {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Suppress
    var activityResource: ActivityResource? = null
        private set

    override fun onResume() {
        super.onResume()
        activityResource?.layer?.onResume(getViewModel())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityBuilder = ActivityBuilder()
        activityResource = provideActivityResource(activityBuilder)

        val themeRes = activityResource?.themeRes
        if (themeRes != null && themeRes != 0) setTheme(themeRes)

        val view = activityResource?.onCreateView(layoutInflater, null)
        setContentView(view)

        activityResource?.onCreateViewModel(this, viewModelFactory, savedInstanceState)
        activityResource?.onAttachLifecycleOwner(this)

        initialiseToolbar()

        val systemUiVisibility = activityResource?.systemUiVisibility
        if (systemUiVisibility != null && systemUiVisibility != 0) window.decorView.systemUiVisibility = systemUiVisibility

        activityResource?.layer?.onCreate(getViewModel(), savedInstanceState)
    }

    private fun initialiseToolbar() {
        if (activityResource?.hideSupportBar == true) supportActionBar?.hide()
        else {
            val toolbarViewId = activityResource?.toolbarViewId
            val toolbarTitle = activityResource?.toolbarTitle

            if (toolbarViewId != null) {
                setSupportActionBar(findViewById(toolbarViewId))

                val displayHomeAsUpEnabled = activityResource?.displayHomeAsUpEnabled ?: false
                supportActionBar?.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)

                if (toolbarTitle != null) supportActionBar?.title = toolbarTitle

                val toolbarIcon = activityResource?.toolbarIcon
                if (toolbarIcon != null) supportActionBar?.setIcon(toolbarIcon)
            } else if (toolbarTitle != null) title = toolbarTitle
        }
    }

    private fun getFragments(): List<Fragment?> {
        val hasNavHostFragment = activityResource?.hasNavHostFragment
        return if (hasNavHostFragment == true) {
            val navHostFragment = supportFragmentManager.fragments.firstOrNull() as? NavHostFragment
            navHostFragment?.childFragmentManager?.fragments ?: emptyList()
        } else supportFragmentManager.fragments
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuId = activityResource?.menuId
        if (menuId != null) {
            val inflater = menuInflater
            inflater.inflate(menuId, menu)
        }

        var hasHandledCreateOptionsMenu = false
        getFragments().forEach { fragment ->
            if (fragment is ArchTreeFragment && fragment.isVisible) {
                val fragmentHasHandledCreateOptionsMenu = fragment.onFragmentCreateOptionsMenu(menu)
                if (!hasHandledCreateOptionsMenu) {
                    hasHandledCreateOptionsMenu = fragmentHasHandledCreateOptionsMenu

                    if (fragmentHasHandledCreateOptionsMenu) return true //has been handled by fragment
                }
            }
        }

        if (!hasHandledCreateOptionsMenu) {
            val shouldRunDefaultCreateOptionsMenu = getViewModel()?.onCreateOptionsMenu(menu)
                    ?: false
            if (!shouldRunDefaultCreateOptionsMenu) return onDefaultCreateOptionsMenu(menu)
        }

        return true
    }

    open fun onDefaultCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        var hasHandledBackPressed = false
        getFragments().forEach { fragment ->
            if (fragment is ArchTreeFragment && fragment.isVisible) {
                val fragmentHasHandledBackPressed = fragment.onBackPressed()
                if (!hasHandledBackPressed) {
                    hasHandledBackPressed = fragmentHasHandledBackPressed

                    if (fragmentHasHandledBackPressed) return //has been handled by fragment
                }
            }
        }

        if (!hasHandledBackPressed) {
            val shouldRunDefaultBackPressed = getViewModel()?.onBackPressed() ?: false
            if (!shouldRunDefaultBackPressed) onDefaultBackPressed()
        }
    }

    open fun onDefaultBackPressed() {
        super.onBackPressed()
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var hasHandledActivityResult = false
        getFragments().forEach { fragment ->
            if (fragment is ArchTreeFragment && fragment.isVisible) {
                val fragmentHasHandledActivityResult = fragment.onFragmentActivityResult(requestCode, resultCode, data)
                if (!hasHandledActivityResult) {
                    hasHandledActivityResult = fragmentHasHandledActivityResult

                    if (fragmentHasHandledActivityResult) return //has been handled by fragment
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
        getFragments().forEach { fragment ->
            if (fragment is ArchTreeFragment && fragment.isVisible) {
                val fragmentHasHandledRequestPermissionResult = fragment.onFragmentRequestPermissionsResult(requestCode, permissions, grantResults)
                if (!hasHandledRequestPermissionResult) {
                    hasHandledRequestPermissionResult = fragmentHasHandledRequestPermissionResult

                    if (fragmentHasHandledRequestPermissionResult) return //has been handled by fragment
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

    override fun onConfigurationChanged(newConfig: Configuration) {
        var hasConfigurationChanged = false
        getFragments().forEach { fragment ->
            if (fragment is ArchTreeFragment && fragment.isVisible) {
                val fragmentHasConfigurationChanged = fragment.onFragmentConfigurationChanged(newConfig)
                if (!hasConfigurationChanged) {
                    hasConfigurationChanged = fragmentHasConfigurationChanged

                    if (fragmentHasConfigurationChanged) return //has been handled by fragment
                }
            }
        }

        if (!hasConfigurationChanged) {
            val shouldRunDefaultConfigurationChanged = getViewModel()?.onConfigurationChanged(newConfig)
                    ?: false
            if (!shouldRunDefaultConfigurationChanged) onDefaultConfigurationChanged(newConfig)
        }
    }

    open fun onDefaultConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    @SuppressLint("MissingSuperCall")
    override fun onNewIntent(intent: Intent?) {
        var hasNewIntentHandled = false
        getFragments().forEach { fragment ->
            if (fragment is ArchTreeFragment && fragment.isVisible) {
                val fragmentHasNewIntentHandled = fragment.onFragmentNewIntent(intent)
                if (!hasNewIntentHandled) {
                    hasNewIntentHandled = fragmentHasNewIntentHandled

                    if (fragmentHasNewIntentHandled) return //has been handled by fragment
                }
            }
        }

        if (!hasNewIntentHandled) {
            val shouldRunDefaultNewIntent = getViewModel()?.onNewIntent(intent)
                    ?: false
            if (!shouldRunDefaultNewIntent) onDefaultNewIntent(intent)
        }
    }

    open fun onDefaultNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var hasHandledOptionsItemSelected = false
        getFragments().forEach { fragment ->
            if (fragment is ArchTreeFragment && fragment.isVisible) {
                val fragmentHasHandledOptionsItemSelected = fragment.onOptionsItemSelected(item)
                if (!hasHandledOptionsItemSelected) {
                    hasHandledOptionsItemSelected = fragmentHasHandledOptionsItemSelected

                    if (fragmentHasHandledOptionsItemSelected) {
                        return true //has been handled by fragment
                    }
                }
            }
        }

        if (!hasHandledOptionsItemSelected) {
            val shouldRunDefaultOptionsItemSelected = getViewModel()?.onOptionsItemSelected(item)
                    ?: false
            if (!shouldRunDefaultOptionsItemSelected) return onDefaultOptionsItemSelected(item)
        }

        return true
    }

    open fun onDefaultOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            else -> getViewModel()?.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        getViewModel()?.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        getViewModel()?.onRestoreInstanceState(savedInstanceState)
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        activityResource?.layer?.onStart(getViewModel())
    }

    override fun onStop() {
        super.onStop()
        activityResource?.layer?.onStop(getViewModel())
    }

    override fun onPause() {
        super.onPause()
        activityResource?.layer?.onPause(getViewModel())
    }

    override fun onDestroy() {
        super.onDestroy()
        activityResource?.layer?.onDestroy(getViewModel())
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    open fun getView(): View? {
        return window.decorView.findViewById(android.R.id.content)
    }

    open fun getViewModel(): BaseViewModel? {
        return activityResource?.viewModel
    }

    open fun getBinding(): ViewDataBinding? {
        return activityResource?.binding
    }

    open fun getCustomBundle(): Bundle? {
        return activityResource?.customBundle
    }
}
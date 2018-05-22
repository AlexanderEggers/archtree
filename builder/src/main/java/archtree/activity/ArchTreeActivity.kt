package archtree.activity

import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import archtree.viewmodel.BaseViewModel
import autotarget.util.HasFragmentFlow
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

        if (activityResource?.title != null) {
            title = activityResource?.title
        }

        if (getBinding() != null) {
            activityResource?.getLayer()?.onResume(getViewModel(), getBinding(), getBundle())
        } else {
            activityResource?.getLayer()?.onResume(getViewModel(), getView(), getBundle())
        }
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

        if (getBinding() != null) {
            activityResource?.getLayer()?.onCreate(getViewModel(), getBinding(), getBundle())
        } else {
            activityResource?.getLayer()?.onCreate(getViewModel(), getView(), getBundle())
        }

        if(activityResource?.hideSupportBar == true) {
            supportActionBar?.hide()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        getViewModel()?.sendActivityResult(requestCode, resultCode, data, getBundle())
    }

    override fun onStop() {
        super.onStop()

        if (getBinding() != null) {
            activityResource?.getLayer()?.onStop(getViewModel(), getBinding())
        } else {
            activityResource?.getLayer()?.onStop(getViewModel(), getView())
        }
    }

    override fun onShowNextFragment(containerId: Int, state: Int, addToBackStack: Boolean, clearBackStack: Boolean, bundle: Bundle?): Boolean {
        return activityResource?.fragmentFlow?.onShowNextFragment(containerId, state, addToBackStack, clearBackStack, bundle) ?: false
    }

    fun getActivityResource(): ActivityResource<ViewModel>? {
        return activityResource
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    fun getView(): View? {
        return window.decorView.findViewById(android.R.id.content)
    }

    fun getViewModel(): ViewModel? {
        return activityResource?.viewModel
    }

    fun getBinding(): ViewDataBinding? {
        return activityResource?.binding
    }

    private fun getBundle(): Bundle? {
        return activityResource?.bundle
    }
}
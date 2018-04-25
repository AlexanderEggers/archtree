package org.archtree.activity

import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import org.autotarget.util.HasFragmentFlow
import javax.inject.Inject
import dagger.android.DispatchingAndroidInjector
import android.os.Bundle
import org.archtree.viewmodel.ViewModelBase

abstract class ArchTreeActivity<ViewModel: ViewModelBase>: AppCompatActivity(),
        HasSupportFragmentInjector, HasFragmentFlow, HasActivityBuilder<ViewModel> {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var activityResource: ActivityResource<ViewModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityBuilder = ActivityBuilder<ViewModel>()
        activityResource = provideActivityResource(activityBuilder)
    }

    override fun onShowNextFragment(containerId: Int, state: Int, addToBackStack: Boolean, clearBackStack: Boolean, bundle: Bundle?) {

    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}
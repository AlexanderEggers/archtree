package org.archtree.fragment

import org.archtree.viewmodel.BaseViewModel

interface HasFragmentBuilder<ViewModel : BaseViewModel> {

    fun provideFragmentResource(builder: FragmentBuilder<ViewModel>): FragmentResource<ViewModel>
}
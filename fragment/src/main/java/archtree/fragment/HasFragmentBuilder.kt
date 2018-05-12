package archtree.fragment

import archtree.viewmodel.BaseViewModel

interface HasFragmentBuilder<ViewModel : BaseViewModel> {

    fun provideFragmentResource(builder: FragmentBuilder<ViewModel>): FragmentResource<ViewModel>
}
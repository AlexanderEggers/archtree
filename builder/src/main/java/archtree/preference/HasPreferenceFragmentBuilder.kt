package archtree.preference

import archtree.viewmodel.BaseViewModel

interface HasPreferenceFragmentBuilder<ViewModel : BaseViewModel> {

    fun provideFragmentResource(builder: PreferenceFragmentBuilder<ViewModel>): PreferenceFragmentResource<ViewModel>
}
package archtree.preference

import archtree.fragment.FragmentLayer
import archtree.viewmodel.BaseViewModel

abstract class PreferenceFragmentLayer<in ViewModel : BaseViewModel> : FragmentLayer<ViewModel>()
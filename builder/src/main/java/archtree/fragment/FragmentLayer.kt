package archtree.fragment

import archtree.ArchTreeLayer
import archtree.viewmodel.BaseViewModel

abstract class FragmentLayer<in ViewModel : BaseViewModel> : ArchTreeLayer<ViewModel>()
package archtree.activity

import archtree.ArchTreeLayer
import archtree.viewmodel.BaseViewModel

abstract class ActivityLayer<in ViewModel : BaseViewModel> : ArchTreeLayer<ViewModel>()
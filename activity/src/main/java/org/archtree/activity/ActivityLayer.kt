package org.archtree.activity

import org.archtree.ArchTreeLayer
import org.archtree.viewmodel.BaseViewModel

abstract class ActivityLayer<in ViewModel : BaseViewModel> : ArchTreeLayer<ViewModel>()
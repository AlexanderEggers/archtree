package org.demo.archtree

import archknife.annotation.ProvideViewModel
import archtree.viewmodel.BaseViewModel
import javax.inject.Inject

@ProvideViewModel
class HasNoViewModel
@Inject constructor(): BaseViewModel()
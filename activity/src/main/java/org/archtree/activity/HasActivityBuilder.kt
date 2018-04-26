package org.archtree.activity

import org.archtree.viewmodel.BaseViewModel

interface HasActivityBuilder<ViewModel : BaseViewModel> {

    fun provideActivityResource(builder: ActivityBuilder<ViewModel>): ActivityResource<ViewModel>
}
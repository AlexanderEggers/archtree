package org.archtree.activity

import org.archtree.viewmodel.ViewModelBase

interface HasActivityBuilder<ViewModel: ViewModelBase> {

    fun provideActivityResource(builder: ActivityBuilder<ViewModel>): ActivityResource<ViewModel>
}
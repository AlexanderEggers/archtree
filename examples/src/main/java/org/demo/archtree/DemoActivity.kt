package org.demo.archtree

import archknife.annotation.ProvideActivity
import archtree.activity.ActivityBuilder
import archtree.activity.ActivityResource
import archtree.activity.ArchTreeActivity
import autotarget.annotation.ActivityTarget

@ActivityTarget
@ProvideActivity
class DemoActivity: ArchTreeActivity<DemoActivityViewModel>() {

    override fun provideActivityResource(builder: ActivityBuilder<DemoActivityViewModel>): ActivityResource<DemoActivityViewModel> {
        return builder.setViewModel(DemoActivityViewModel::class.java, BR.viewModel)
                .setLayoutId(R.layout.demo_activity_layout)
                .setTitle("Demo Activity")
                .build()
    }
}
package org.demo.archtree

import org.archknife.annotation.ProvideActivity
import org.archtree.activity.ActivityBuilder
import org.archtree.activity.ActivityResource
import org.archtree.activity.ArchTreeActivity

@ProvideActivity
class DemoActivity: ArchTreeActivity<DemoActivityViewModel>() {

    override fun provideActivityResource(builder: ActivityBuilder<DemoActivityViewModel>): ActivityResource<DemoActivityViewModel> {
        return builder.setViewModel(DemoActivityViewModel::class.java, BR.viewModel)
                .setLayoutId(R.layout.demo_activity_layout)
                .setTitle("Demo Activity")
                .build()
    }
}
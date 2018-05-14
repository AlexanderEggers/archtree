package org.demo.archtree

import android.databinding.ViewDataBinding
import android.os.Bundle
import archknife.annotation.ProvideActivity
import archtree.activity.ActivityBuilder
import archtree.activity.ActivityLayer
import archtree.activity.ActivityResource
import archtree.activity.ArchTreeActivity
import autotarget.annotation.ActivityTarget
import autotarget.util.ContextInjector

@ActivityTarget
@ProvideActivity
class DemoActivity: ArchTreeActivity<DemoActivityViewModel>() {

    override fun provideActivityResource(builder: ActivityBuilder<DemoActivityViewModel>): ActivityResource<DemoActivityViewModel> {
        return builder.setViewModel(DemoActivityViewModel::class.java, BR.viewModel)
                .setLayoutId(R.layout.demo_activity_layout)
                .setTitle("Demo Activity")
                .build(object: ActivityLayer<DemoActivityViewModel>(){

                    override fun onCreate(viewModel: DemoActivityViewModel?, binding: ViewDataBinding?, bundle: Bundle?) {
                        super.onCreate(viewModel, binding, bundle)
                        ContextInjector.inject(this@DemoActivity)
                    }
                })
    }
}
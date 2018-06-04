package org.demo.archtree

import archknife.annotation.ProvideFragment
import archtree.fragment.ArchTreeFragment
import archtree.fragment.FragmentBuilder
import archtree.fragment.FragmentResource
import archtree.viewmodel.HasNoViewModel
import autotarget.annotation.FragmentTarget

@FragmentTarget(R.id.fragment_container)
@ProvideFragment([DemoActivity::class])
class DemoFragment: ArchTreeFragment<HasNoViewModel>() {

    override fun provideFragmentResource(builder: FragmentBuilder<HasNoViewModel>): FragmentResource<HasNoViewModel> {
        return builder.setLayoutId(R.layout.fragment_layout)
                .build()
    }
}
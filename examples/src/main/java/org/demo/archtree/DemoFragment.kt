package org.demo.archtree

import archknife.annotation.ProvideFragment
import archtree.preference.ArchTreePreferenceFragment
import archtree.preference.PreferenceFragmentBuilder
import archtree.preference.PreferenceFragmentResource
import autotarget.annotation.FragmentTarget

@FragmentTarget(R.id.fragment_container)
@ProvideFragment([DemoActivity::class])
class DemoFragment: ArchTreePreferenceFragment<HasNoViewModel>() {

    override fun provideFragmentResource(builder: PreferenceFragmentBuilder<HasNoViewModel>): PreferenceFragmentResource<HasNoViewModel> {
        return builder.setLayoutId(R.layout.fragment_layout)
                .setPreferenceFromResource(R.xml.demo_settings, R.id.settings_container)
                .addStaticPreferenceValue("build_number", BuildConfig.APPLICATION_ID, false)
                .build()
    }
}
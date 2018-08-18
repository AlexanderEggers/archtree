package org.demo.archtree

import android.app.Activity
import android.databinding.DataBindingComponent

class ActivityDatabindingComponent(activity: Activity): DataBindingComponent {

    private val activityDemoAdapter = ActivityDemoAdapter(activity)

    override fun getActivityDemoAdapter(): ActivityDemoAdapter {
        return activityDemoAdapter
    }
}
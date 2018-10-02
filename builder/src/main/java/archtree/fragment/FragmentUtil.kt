package archtree.fragment

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.view.View
import archtree.ArchTreeResource

internal fun refreshFragmentToolbar(activity: Activity?, rootView: View?, fragmentResource: ArchTreeResource<*>?) {
    val toolbarViewId = fragmentResource?.toolbarViewId
    val toolbarTitle = fragmentResource?.toolbarTitle

    if (toolbarViewId != null) {
        val supportActivity = activity as? AppCompatActivity

        if (fragmentResource.activityToolbar) {
            supportActivity?.setSupportActionBar(supportActivity.findViewById(toolbarViewId))
        } else {
            supportActivity?.setSupportActionBar(rootView?.findViewById(toolbarViewId))
        }

        val displayHomeAsUpEnabled = fragmentResource.displayHomeAsUpEnabled
        supportActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)

        if (toolbarTitle != null) {
            supportActivity?.supportActionBar?.title = toolbarTitle
        }

        val toolbarIcon = fragmentResource.toolbarIcon
        if (toolbarIcon != null) {
            supportActivity?.supportActionBar?.setIcon(toolbarIcon)
        }
    } else if (toolbarTitle != null) {
        activity?.title = toolbarTitle
    }
}
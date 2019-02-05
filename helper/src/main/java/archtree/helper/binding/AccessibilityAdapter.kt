package archtree.helper.binding

import android.view.View
import android.view.accessibility.AccessibilityNodeInfo
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("archtree_accessibilityClickAction", "archtree_accessibilityLongClickAction", requireAll = false)
fun setAccessibilityAction(view: View, clickAction: Int?, longClickAction: Int?) {

    ViewCompat.setAccessibilityDelegate(view, object : AccessibilityDelegateCompat() {

        override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(host, info)

            if (clickAction != null) {
                val click = AccessibilityNodeInfoCompat.AccessibilityActionCompat(
                        AccessibilityNodeInfo.ACTION_CLICK,
                        host.resources?.getString(clickAction))
                info.addAction(click)
            }

            if (longClickAction != null) {
                val longClick = AccessibilityNodeInfoCompat.AccessibilityActionCompat(
                        AccessibilityNodeInfo.ACTION_LONG_CLICK,
                        host.resources?.getString(longClickAction))
                info.addAction(longClick)
            }
        }
    })
}
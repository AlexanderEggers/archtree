package archtree.helper.binding

import android.view.View
import android.view.accessibility.AccessibilityNodeInfo
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("accessibilityClickAction", "accessibilityLongClickAction")
fun setAccessibilityAction(view: View, clickAction: String?, longClickAction: String?) {

    ViewCompat.setAccessibilityDelegate(view, object : AccessibilityDelegateCompat() {

        override fun onInitializeAccessibilityNodeInfo(host: View?, info: AccessibilityNodeInfoCompat?) {
            super.onInitializeAccessibilityNodeInfo(host, info)

            if (clickAction != null) {
                val click = AccessibilityNodeInfoCompat.AccessibilityActionCompat(AccessibilityNodeInfo.ACTION_LONG_CLICK, clickAction)
                info?.addAction(click)
            }

            if (longClickAction != null) {
                val longClick = AccessibilityNodeInfoCompat.AccessibilityActionCompat(AccessibilityNodeInfo.ACTION_LONG_CLICK, longClickAction)
                info?.addAction(longClick)
            }
        }
    })
}
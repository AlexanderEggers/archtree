package archtree.helper.binding

import android.view.View
import android.view.accessibility.AccessibilityNodeInfo
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("archtree_accessibilityClickAction", "archtree_accessibilityLongClickAction", requireAll = false)
fun setAccessibilityActions(view: View, clickAction: String?, longClickAction: String?) {
    assignAccessibilityActionsToView(view, clickAction, longClickAction)
}

@BindingAdapter("archtree_accessibilityClickActionRes", "archtree_accessibilityLongClickActionRes", requireAll = false)
fun setAccessibilityActionsRes(view: View, clickAction: Int?, longClickAction: Int?) {

    val clickActionValue = if(clickAction != null) view.resources.getString(clickAction) else null
    val longClickActionValue = if(longClickAction != null) view.resources.getString(longClickAction) else null

    assignAccessibilityActionsToView(view, clickActionValue, longClickActionValue)
}

fun assignAccessibilityActionsToView(view: View, clickAction: String?, longClickAction: String?) {

    ViewCompat.setAccessibilityDelegate(view, object : AccessibilityDelegateCompat() {

        override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(host, info)

            if (clickAction != null) {
                val click = AccessibilityNodeInfoCompat.AccessibilityActionCompat(
                        AccessibilityNodeInfo.ACTION_CLICK,
                        clickAction)
                info.addAction(click)
            }

            if (longClickAction != null) {
                val longClick = AccessibilityNodeInfoCompat.AccessibilityActionCompat(
                        AccessibilityNodeInfo.ACTION_LONG_CLICK,
                        longClickAction)
                info.addAction(longClick)
            }
        }
    })
}
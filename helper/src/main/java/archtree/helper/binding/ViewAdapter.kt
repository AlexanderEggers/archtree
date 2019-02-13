package archtree.helper.binding

import android.view.View
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("archtree_viewHeightForNotch")
fun setViewHeightForNotch(view: View, visible: Boolean?) {
    if (visible == true) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val layoutparams = v.layoutParams
            layoutparams.height = insets.systemWindowInsetTop
            v.layoutParams = layoutparams

            insets
        }
    }
}
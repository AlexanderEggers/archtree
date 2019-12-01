package archtree.helper.binding

import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter
import androidx.transition.Transition
import androidx.transition.TransitionManager

@BindingAdapter("archtree_visibility")
fun View.setVisibility(visible: Boolean?) {
    visibility = if (visible == true) View.VISIBLE else View.GONE
}

@BindingAdapter("archtree_visibility", "archtree_visibilityTransition")
fun View.setVisibilityWithAnimation(visible: Boolean?, transition: Transition) {
    val parent = parent
    if (parent is ViewGroup) TransitionManager.beginDelayedTransition(parent, transition)

    ViewCompat.setZ(this, -1f)
    setVisibility(visible)
}
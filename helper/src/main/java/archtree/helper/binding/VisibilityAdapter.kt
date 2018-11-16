package archtree.helper.binding

import android.databinding.BindingAdapter
import android.support.annotation.NonNull
import android.support.transition.*
import android.support.v4.view.ViewCompat
import android.view.View
import android.view.ViewGroup

@BindingAdapter("archtree_visibility")
fun setVisibility(@NonNull view: View, visible: Boolean?) {
    visible?.run {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}

@BindingAdapter("archtree_visibility", "archtree_visibilityTransition")
fun setVisibilityWithAnimation(@NonNull view: View, visible: Boolean?, transition: Transition) {
    visible?.run {
        setVisibilityWithAnimation(view, visible, transition)
    }
}

private fun setVisibilityWithAnimation(@NonNull view: View, visible: Boolean, transition: Transition) {
    val parent = view.parent
    if (parent is ViewGroup) TransitionManager.beginDelayedTransition(parent, transition)

    ViewCompat.setZ(view, -1f)
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

fun transitionSet(vararg transitions: Transition): Transition {
    val transitionSet = TransitionSet()

    for (transition in transitions) {
        transitionSet.addTransition(transition)
    }

    return transitionSet
}
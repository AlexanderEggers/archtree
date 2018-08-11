package archtree.helper.binding

import android.databinding.BindingAdapter
import android.support.annotation.NonNull
import android.support.transition.*
import android.support.v4.view.ViewCompat
import android.view.Gravity.TOP
import android.view.View
import android.view.ViewGroup

class VisibilityAdapter {

    companion object {

        @JvmStatic
        @BindingAdapter("archtree_visibility")
        fun setVisibility(@NonNull view: View, visible: Boolean?) {
            visible?.run {
                view.visibility = if (visible) View.VISIBLE else View.GONE
            }
        }

        @JvmStatic
        @BindingAdapter("archtree_visibilityAnim")
        fun setVisibilityWithAnimation(@NonNull view: View, visible: Boolean?) {
            visible?.run {
                setVisibilityWithAnimation(view, visible, transitionSet(
                        Slide(TOP),
                        Fade()
                ))
            }
        }

        private fun setVisibilityWithAnimation(@NonNull view: View, visible: Boolean, transition: Transition) {
            val parent = view.parent
            if (parent is ViewGroup) TransitionManager.beginDelayedTransition(parent, transition)

            ViewCompat.setZ(view, -1f)
            view.visibility = if (visible) View.VISIBLE else View.GONE
        }

        private fun transitionSet(vararg transitions: Transition): Transition {
            val transitionSet = TransitionSet()

            for (transition in transitions) {
                transitionSet.addTransition(transition)
            }

            return transitionSet
        }
    }
}
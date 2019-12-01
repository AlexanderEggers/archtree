package archtree.helper.binding

import android.content.res.Resources
import android.graphics.PorterDuff
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("archtree_drawableRes")
fun ImageView.setDrawableRes(icon: Int?) {
    icon?.run {
        val drawable = try {
            ContextCompat.getDrawable(context, icon)
        } catch (e: Resources.NotFoundException) {
            null
        }
        setImageDrawable(drawable)
    } ?: setImageBitmap(null)
}

@BindingAdapter("archtree_imageTintColor")
fun ImageView.setTintColor(value: Int?) {
    value?.run {
        setColorFilter(ContextCompat.getColor(context, value), PorterDuff.Mode.MULTIPLY)
    }
}
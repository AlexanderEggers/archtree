package archtree.helper.binding

import android.content.res.Resources
import android.graphics.PorterDuff
import android.os.Build
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter

@Suppress("DEPRECATION")
@BindingAdapter("archtree_htmlText")
fun setHtml(textView: TextView, html: String?) {
    if (html != null) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.text = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            textView.text = Html.fromHtml(html)
        }
        textView.movementMethod = LinkMovementMethod.getInstance()
    }
}

@BindingAdapter("archtree_customFont")
fun setFont(view: TextView, value: Int?) {
    value?.run {
        val typeface = try {
            ResourcesCompat.getFont(view.context, value)
        } catch (e: Resources.NotFoundException) {
            null
        }
        typeface?.run {
            view.typeface = typeface
        }
    }
}

@BindingAdapter("archtree_drawableRes")
fun setDrawableRes(view: ImageView, icon: Int?) {
    icon?.run {
        val drawable = try {
            ContextCompat.getDrawable(view.context, icon)
        } catch (e: Resources.NotFoundException) {
            null
        }
        view.setImageDrawable(drawable)
    } ?: view.setImageBitmap(null)
}

@BindingAdapter("archtree_textRes")
fun setTextRes(view: TextView, value: Int?) {
    value?.run {
        view.text = try {
            view.resources.getString(value)
        } catch (e: Resources.NotFoundException) {
            ""
        }
    }
}

@BindingAdapter("archtree_textColorRes")
fun setTextColorRes(view: TextView, value: Int?) {
    value?.run {
        try {
            ContextCompat.getColor(view.context, value)
        } catch (e: Resources.NotFoundException) {
            null
        }?.let { textColor ->
            view.setTextColor(textColor)
        }
    }
}

@BindingAdapter("archtree_backgroundRes")
fun setBackgroundRes(view: View, value: Int?) {
    value?.run {
        try {
            view.setBackgroundResource(value)
        } catch (e: Resources.NotFoundException) {
            view.setBackgroundResource(0)
        }
    } ?: view.setBackgroundResource(0)
}

@BindingAdapter("archtree_imageTintColor")
fun setTintColor(view: ImageView, value: Int?) {
    value?.run {
        view.setColorFilter(ContextCompat.getColor(view.context, value), PorterDuff.Mode.MULTIPLY)
    }
}
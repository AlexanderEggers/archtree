package archtree.helper.binding

import android.content.res.Resources
import android.graphics.PorterDuff
import android.os.Build
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.databinding.BindingAdapter

@Suppress("DEPRECATION")
@BindingAdapter("archtree_htmlText", "archtree_htmlTextAsync", requireAll = false)
fun setHtml(textView: TextView, html: String?, isAsync: Boolean?) {
    if (html != null) {
        val text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else Html.fromHtml(html)

        if (isAsync == true && textView is AppCompatTextView) {
            val params = TextViewCompat.getTextMetricsParams(textView)
            textView.setTextFuture(PrecomputedTextCompat.getTextFuture(text, params, null))
        } else textView.text = text

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

@BindingAdapter("archtree_textRes", "archtree_textResAsync", requireAll = false)
fun setTextRes(view: TextView, value: Int?, isAsync: Boolean?) {
    value?.run {
        val text = try {
            view.resources.getString(value)
        } catch (e: Resources.NotFoundException) {
            ""
        }

        if (isAsync == true && view is AppCompatTextView) {
            val params = TextViewCompat.getTextMetricsParams(view)
            view.setTextFuture(PrecomputedTextCompat.getTextFuture(text, params, null))
        } else view.text = text
    }
}

@BindingAdapter("archtree_asyncText")
fun setAsyncText(view: AppCompatTextView, text: String?) {
    text?.run {
        val params = TextViewCompat.getTextMetricsParams(view)
        view.setTextFuture(PrecomputedTextCompat.getTextFuture(text, params, null))
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
package archtree.helper.binding

import android.content.res.Resources
import android.databinding.BindingAdapter
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.text.Html
import android.widget.ImageView
import android.widget.TextView

@Suppress("DEPRECATION")
@BindingAdapter("archtree_setHtml")
fun setHtml(textView: TextView, html: String?) {
    if (html != null) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.text = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            textView.text = Html.fromHtml(html)
        }
    }
}

@BindingAdapter("archtree_custom_font")
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

@BindingAdapter("archtree_icon_src")
fun setIcon(view: ImageView, icon: Int?) {
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
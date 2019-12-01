package archtree.helper.binding

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.databinding.BindingAdapter

@Suppress("DEPRECATION")
@BindingAdapter("archtree_htmlText", "archtree_htmlTextAsync", requireAll = false)
fun TextView.setHtml(html: String?, isAsync: Boolean?) {
    html?.run {
        val htmlText = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else Html.fromHtml(html)

        if (isAsync == true && this@setHtml is AppCompatTextView) {
            val params = TextViewCompat.getTextMetricsParams(this@setHtml)
            setTextFuture(PrecomputedTextCompat.getTextFuture(htmlText, params, null))
        } else text = htmlText

        movementMethod = LinkMovementMethod.getInstance()
    }
}

@BindingAdapter("archtree_customFont")
fun TextView.setFont(value: Int?) {
    value?.run {
        val newTypeface = try {
            ResourcesCompat.getFont(context, value)
        } catch (e: Resources.NotFoundException) {
            null
        }
        newTypeface?.run {
            typeface = newTypeface
        }
    }
}

@BindingAdapter("archtree_textRes", "archtree_textResAsync", requireAll = false)
fun TextView.setTextRes(value: Int?, isAsync: Boolean?) {
    value?.run {
        val textValue = try {
            resources.getString(value)
        } catch (e: Resources.NotFoundException) {
            ""
        }
        if (isAsync == true && this@setTextRes is AppCompatTextView) {
            val params = TextViewCompat.getTextMetricsParams(this@setTextRes)
            setTextFuture(PrecomputedTextCompat.getTextFuture(textValue, params, null))
        } else text = textValue
    }
}

@BindingAdapter("archtree_textColorRes")
fun TextView.setTextColorRes(value: Int?) {
    value?.run {
        try {
            ContextCompat.getColor(context, value)
        } catch (e: Resources.NotFoundException) {
            null
        }?.let { textColor ->
            setTextColor(textColor)
        }
    }
}

@BindingAdapter("archtree_asyncText")
fun AppCompatTextView.setAsyncText(text: String?) {
    text?.run {
        val params = TextViewCompat.getTextMetricsParams(this@setAsyncText)
        setTextFuture(PrecomputedTextCompat.getTextFuture(text, params, null))
    }
}
package archtree.helper.binding

import android.databinding.BindingAdapter
import android.os.Build
import android.support.v4.content.res.ResourcesCompat
import android.text.Html
import android.widget.ImageView
import android.widget.TextView

class CommonAdapter {

    companion object {

        @Suppress("DEPRECATION")
        @JvmStatic
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

        @JvmStatic
        @BindingAdapter("archtree_custom_font")
        fun setFont(view: TextView, value: Int?) {
            value?.run {
                view.typeface = ResourcesCompat.getFont(view.context, value)
            }
        }

        @JvmStatic
        @BindingAdapter("archtree_icon_src")
        fun setIcon(view: ImageView, icon: Int?) {
            icon?.run {
                view.setImageResource(icon)
            } ?: view.setImageBitmap(null)
        }

        @JvmStatic
        @BindingAdapter("archtree_textRes")
        fun setTextRes(view: TextView, value: Int?) {
            value?.run {
                view.setText(value)
            }
        }
    }
}
package archtree.helper.binding

import android.databinding.BindingAdapter
import android.os.Build
import android.text.Html
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
    }
}
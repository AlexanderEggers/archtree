package org.demo.archtree

import android.app.Activity
import androidx.databinding.BindingAdapter
import android.widget.TextView

class ActivityDemoAdapter(private val activity: Activity) {

    @BindingAdapter("testGetText")
    fun testGetText(textView: TextView, value: String) {
        textView.text = value
    }
}
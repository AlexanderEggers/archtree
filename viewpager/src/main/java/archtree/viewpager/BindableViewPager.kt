package archtree.viewpager

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet

class BindableViewPager: ViewPager {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
}
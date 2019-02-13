package archtree.helper.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import archtree.helper.R

class BoundedCoordinatorLayout : CoordinatorLayout {

    private var boundedWidth: Int = 0
    private var boundedHeight: Int = 0

    constructor(context: Context) : super(context) {
        boundedWidth = 0
        boundedHeight = 0
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.BoundedCoordinatorLayout)
        boundedWidth = a.getDimensionPixelSize(R.styleable.BoundedCoordinatorLayout_bounded_width, 0)
        boundedHeight = a.getDimensionPixelSize(R.styleable.BoundedCoordinatorLayout_bounded_height, 0)
        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var newWidthMeasureSpec = widthMeasureSpec
        var newHeightMeasureSpec = heightMeasureSpec

        // Adjust width as necessary
        val measuredWidth = View.MeasureSpec.getSize(widthMeasureSpec)
        if (boundedWidth in 1..(measuredWidth - 1)) {
            val measureMode = View.MeasureSpec.getMode(widthMeasureSpec)
            newWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(boundedWidth, measureMode)
        }

        // Adjust height as necessary
        val measuredHeight = View.MeasureSpec.getSize(heightMeasureSpec)
        if (boundedHeight in 1..(measuredHeight - 1)) {
            val measureMode = View.MeasureSpec.getMode(heightMeasureSpec)
            newHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(boundedHeight, measureMode)
        }

        super.onMeasure(newWidthMeasureSpec, newHeightMeasureSpec)
    }
}

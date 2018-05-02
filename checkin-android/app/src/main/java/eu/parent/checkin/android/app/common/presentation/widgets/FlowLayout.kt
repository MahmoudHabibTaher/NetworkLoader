package eu.parent.android.app.common.presentation.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import eu.parent.android.app.R


class FlowLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ViewGroup(context, attrs, defStyleAttr) {
    private var horizontalSpacing = 0
    private var verticalSpacing = 0

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout)

        try {
            horizontalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_horizontalSpacing,
                    0)
            verticalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_verticalSpacing,
                    0)
        } finally {
            a.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = View.MeasureSpec.getSize(widthMeasureSpec) - paddingRight
        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)

        val growHeight = widthMode != View.MeasureSpec.UNSPECIFIED

        var width = 0
        var height = paddingTop

        var currentWidth = paddingLeft
        var currentHeight = 0

        var breakLine = false
        var newLine = false
        var spacing = 0

        val count = childCount
        for (i in 0 until count) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)

            val lp = child.layoutParams as LayoutParams
            spacing = horizontalSpacing
            if (lp.horizontalSpacing >= 0) {
                spacing = lp.horizontalSpacing
            }

            if (growHeight && (breakLine || currentWidth + child.measuredWidth > widthSize)) {
                height += currentHeight + verticalSpacing
                currentHeight = 0
                width = Math.max(width, currentWidth - spacing)
                currentWidth = paddingLeft
                newLine = true
            } else {
                newLine = false
            }

            lp.x = currentWidth
            lp.y = height

            currentWidth += child.measuredWidth + spacing
            currentHeight = Math.max(currentHeight, child.measuredHeight)

            breakLine = lp.breakLine
        }

        if (!newLine) {
            height += currentHeight
            width = Math.max(width, currentWidth - spacing)
        }

        width += paddingRight
        height += paddingBottom

        setMeasuredDimension(View.resolveSize(width, widthMeasureSpec),
                View.resolveSize(height, heightMeasureSpec))
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val count = childCount
        for (i in 0 until count) {
            val child = getChildAt(i)
            val lp = child.layoutParams as LayoutParams
            child.layout(lp.x, lp.y, lp.x + child.measuredWidth, lp.y + child.measuredHeight)
        }
    }

    override fun checkLayoutParams(p: ViewGroup.LayoutParams): Boolean {
        return p is LayoutParams
    }

    override fun generateDefaultLayoutParams(): LayoutParams =
            LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

    override fun generateLayoutParams(attrs: AttributeSet): LayoutParams =
            LayoutParams(context, attrs)

    override fun generateLayoutParams(p: ViewGroup.LayoutParams): LayoutParams =
            LayoutParams(p.width, p.height)

    class LayoutParams : ViewGroup.LayoutParams {
        var x: Int = 0
        var y: Int = 0

        var horizontalSpacing: Int = 0
        var breakLine: Boolean = false

        constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout_LayoutParams)
            try {
                horizontalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_LayoutParams_layout_horizontalSpacing, -1)
                breakLine = a.getBoolean(R.styleable.FlowLayout_LayoutParams_layout_breakLine, false)
            } finally {
                a.recycle()
            }
        }

        constructor(w: Int, h: Int) : super(w, h) {}
    }
}
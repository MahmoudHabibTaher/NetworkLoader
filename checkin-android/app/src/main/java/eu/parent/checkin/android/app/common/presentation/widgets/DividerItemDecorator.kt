package eu.parent.android.app.common.presentation.widgets

import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.graphics.drawable.Drawable


/**
 * Created by Raed Ezzat on 20/03/2018.
 */
class DividerItemDecorator(var mDivider: Drawable) : RecyclerView.ItemDecoration() {

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val dividerLeft = parent.paddingLeft
        val dividerRight = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0..childCount - 2) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val dividerTop = child.bottom + params.bottomMargin
            val dividerBottom = dividerTop + mDivider.intrinsicHeight

            mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            mDivider.draw(canvas)
        }
    }

}
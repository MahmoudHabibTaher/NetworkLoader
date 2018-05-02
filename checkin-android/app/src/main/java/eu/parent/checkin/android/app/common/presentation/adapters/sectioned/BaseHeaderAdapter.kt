package eu.parent.android.app.common.presentation.adapters.sectioned

import android.support.v7.widget.RecyclerView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.oushangfeng.pinnedsectionitemdecoration.utils.FullSpanUtil

/**
 * Created by mahmoud on 9/19/17.
 */
abstract class BaseHeaderAdapter<T : MultiItemEntity>(data: MutableList<T>?) :
        BaseMultiItemQuickAdapter<T, BaseViewHolder>(data) {
    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_SUB_HEADER = 1
        const val TYPE_DATA = 2
    }

    init {
        addItemTypes()
    }

    abstract fun addItemTypes()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
        FullSpanUtil.onAttachedToRecyclerView(recyclerView, this, TYPE_HEADER)
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder?) {
        super.onViewAttachedToWindow(holder)
        FullSpanUtil.onViewAttachedToWindow(holder, this, TYPE_HEADER)
    }
}
package com.bigo.networkloader.demo.pins.list.presentation

import com.bigo.networkloader.R
import com.bigo.networkloader.demo.pins.list.domain.entities.Pin
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class PinsAdapter(data: MutableList<Pin>?) :
    BaseQuickAdapter<Pin, BaseViewHolder>(R.layout.layout_pin_list_item, data) {
    override fun convert(helper: BaseViewHolder?, item: Pin?) {
        if (helper != null && item != null) {
            with(helper) {
                setText(R.id.user_name_text_view, item.user.name)
            }
        }
    }
}
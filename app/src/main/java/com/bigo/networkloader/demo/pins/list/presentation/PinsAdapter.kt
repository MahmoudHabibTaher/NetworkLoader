package com.bigo.networkloader.demo.pins.list.presentation

import android.widget.ImageView
import com.bigo.networkloader.R
import com.bigo.networkloader.demo.pins.list.domain.entities.Pin
import com.bigo.networkloader.image.ImageLoader
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class PinsAdapter(
    data: MutableList<Pin>?,
    private val imageLoader: ImageLoader
) :
    BaseQuickAdapter<Pin, BaseViewHolder>(R.layout.layout_pin_list_item, data) {
    override fun convert(helper: BaseViewHolder?, item: Pin?) {
        if (helper != null && item != null) {
            with(helper) {
                setText(R.id.user_name_text_view, item.user.name)
                getView<ImageView>(R.id.pin_image_view)?.apply {
                    imageLoader.load(item.urls.regular, this)
                }

                getView<ImageView>(R.id.user_avatar_image_view)?.apply {
                    imageLoader.load(item.user.image.medium, this)
                }
            }
        }
    }
}
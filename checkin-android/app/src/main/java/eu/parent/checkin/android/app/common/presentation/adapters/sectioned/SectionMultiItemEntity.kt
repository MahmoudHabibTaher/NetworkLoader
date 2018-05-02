package eu.parent.android.app.common.presentation.adapters.sectioned

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * Created by mahmoud on 9/19/17.
 */
class SectionMultiItemEntity<T>(var item: T, private val type: Int, var headerTitle: String = "", var isExpanded: Boolean = false) : SerializableMutliItemEntity, MultiItemEntity {
    override fun getItemType(): Int = type
}
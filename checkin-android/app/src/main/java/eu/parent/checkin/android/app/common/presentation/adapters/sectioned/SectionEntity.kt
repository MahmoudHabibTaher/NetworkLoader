package eu.parent.android.app.common.presentation.adapters.sectioned

/**
 * Created by mahmoud on 9/19/17.
 */
class SectionEntity<T>(var item: T, private val type: Int, var headerTitle: String = "",
                       var headerSubTitle: String = "") : SerializableMutliItemEntity {
    override fun getItemType(): Int = type
}
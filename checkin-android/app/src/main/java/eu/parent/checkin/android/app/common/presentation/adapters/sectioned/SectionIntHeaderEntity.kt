package eu.parent.android.app.common.presentation.adapters.sectioned

/**
 * Created by mahmoud on 9/19/17.
 */
class SectionIntHeaderEntity<T>(var item: T, private val type: Int, var headerInt: Int) : SerializableMutliItemEntity {
    override fun getItemType(): Int = type
}
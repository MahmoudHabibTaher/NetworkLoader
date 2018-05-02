package com.parent.domain.children.contacts.mappers

import com.parent.entities.ChildContactPage
import com.parent.entities.ChildContactPageModelView
import com.parent.domain.base.mappers.ModelMapper

/**
 * Created by mahmoud on 10/3/17.
 */
class ChildContactPageViewModelMapper : ModelMapper<ChildContactPageModelView, ChildContactPage> {
    override fun mapTo(item: ChildContactPage): ChildContactPageModelView =
            ChildContactPageModelView(item.currentPage ?: "", item.firstPageUrl
                    ?: "", item.from ?: "",
                    item.lastPage ?: "", item.lastPageUrl ?: "", item.nextPageUrl ?: "", item.path
                    ?: "",
                    item.perPage ?: "")

    override fun mapFrom(item: ChildContactPageModelView): ChildContactPage =
            ChildContactPage(item.currentPage ?: "", item.firstPageUrl
                    ?: "", item.from ?: "",
                    item.lastPage ?: "", item.lastPageUrl ?: "", item.nextPageUrl ?: "", item.path
                    ?: "",
                    item.perPage ?: "")

}
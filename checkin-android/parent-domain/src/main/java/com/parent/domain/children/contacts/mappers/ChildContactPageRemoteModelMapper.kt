package com.parent.domain.children.contacts.mappers

import com.parent.entities.ChildContactPageRemote
import com.parent.entities.ChildContactPage
import com.parent.domain.base.mappers.ModelMapper

/**
 * Created by mahmoud on 10/3/17.
 */
class ChildContactPageRemoteModelMapper : ModelMapper<ChildContactPageRemote, ChildContactPage> {
    override fun mapTo(to: ChildContactPage): ChildContactPageRemote {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mapFrom(item: ChildContactPageRemote): ChildContactPage =
            ChildContactPage(item.currentPage ?: "", item.firstPageUrl
                    ?: "", item.from ?: "",
                    item.lastPage ?: "", item.lastPageUrl ?: "", item.nextPageUrl ?: "", item.path
                    ?: "",
                    item.perPage ?: "")

}
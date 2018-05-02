package com.parent.domain.children.contacts.mappers

import com.parent.entities.ChildContactRelationModelRemote
import com.parent.entities.ChildContactRelationModel
import com.parent.domain.base.mappers.ModelMapper

/**
 * Created by mahmoud on 10/3/17.
 */
class ChildContactRealationRemoteModelMapper : ModelMapper<ChildContactRelationModelRemote, ChildContactRelationModel> {

    override fun mapFrom(item: ChildContactRelationModelRemote): ChildContactRelationModel =
            ChildContactRelationModel(item.id ?: "", item.name ?: "")

    override fun mapTo(item: ChildContactRelationModel): ChildContactRelationModelRemote =
            ChildContactRelationModelRemote(item.id ?: "", item.title ?: "")
}
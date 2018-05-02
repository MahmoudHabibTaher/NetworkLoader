package com.parent.domain.children.contacts.mappers

import com.parent.domain.realm.entities.ChildContactRelationModelRealm
import com.parent.entities.ChildContactRelationModel
import com.parent.domain.base.mappers.ModelMapper

/**
 * Created by mahmoud on 10/3/17.
 */
class ChildContactRelationRealmModelMapper : ModelMapper<ChildContactRelationModelRealm, ChildContactRelationModel> {

    override fun mapFrom(item: ChildContactRelationModelRealm): ChildContactRelationModel =
            ChildContactRelationModel(item.id, item.title)

    override fun mapTo(item: ChildContactRelationModel): ChildContactRelationModelRealm =
            ChildContactRelationModelRealm(item.id, item.title)
}
package com.parent.domain.children.contacts.mappers

import com.parent.entities.ChildContactRelationModel
import com.parent.entities.ChildContactRelationModelView
import com.parent.domain.base.mappers.ModelMapper

/**
 * Created by mahmoud on 10/3/17.
 */
class ChildContactRealationViewModelMapper : ModelMapper<ChildContactRelationModelView, ChildContactRelationModel> {

    override fun mapFrom(item: ChildContactRelationModelView): ChildContactRelationModel =
            ChildContactRelationModel(item.id, item.title)

    override fun mapTo(item: ChildContactRelationModel): ChildContactRelationModelView =
            ChildContactRelationModelView(item.id, item.title)
}
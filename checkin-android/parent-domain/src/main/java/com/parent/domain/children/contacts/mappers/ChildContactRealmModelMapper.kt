package com.parent.domain.children.contacts.mappers

import com.parent.domain.realm.entities.AddressModelRealm
import com.parent.entities.ChildContactModel
import com.parent.domain.realm.entities.ChildContactModelRealm
import com.parent.domain.realm.entities.ChildContactRelationModelRealm
import com.parent.domain.realm.entities.ChildContactRoleModelRealm
import com.parent.entities.AddressModel
import com.parent.entities.ChildContactRelationModel
import com.parent.entities.ChildContactRoleModel
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.base.mappers.mapToWith

/**
 * Created by mahmoud on 10/3/17.
 */
class ChildContactRealmModelMapper(var addressRealmModerMapper: ModelMapper<AddressModelRealm, AddressModel>,
                                   var relationRealmModelMapper: ModelMapper<ChildContactRelationModelRealm, ChildContactRelationModel>,
                                   var roleRealmModelMapper: ModelMapper<ChildContactRoleModelRealm, ChildContactRoleModel>
) : ModelMapper<ChildContactModelRealm, ChildContactModel> {

    override fun mapFrom(item: ChildContactModelRealm): ChildContactModel =
            ChildContactModel(item.id, item.fullName, item.photo,
                    item.relation?.mapFromWith(relationRealmModelMapper) ?: ChildContactRelationModel(),
                    item.childId, item.hasLogin, item.phoneNumber,
                    item.role?.mapFromWith(roleRealmModelMapper) ?: ChildContactRoleModel()
                    , item.email ?: "", item.mobileNumber ?: "",
                    item.address?.mapFromWith(addressRealmModerMapper) ?: AddressModel(), item.hidePhoneNumber ?: "",
                    item.protectedAddress ?: "", item.institutionId,0, item.currentPage,
                    item.firstPageUrl, item.from, item.path,
                    item.perPage)

    override fun mapTo(item: ChildContactModel): ChildContactModelRealm =
            ChildContactModelRealm(item.id, item.fullName, item.photo,
                    item.relation?.mapToWith(relationRealmModelMapper) ?: ChildContactRelationModelRealm(),
                    item.childId, item.hasLogin, item.phoneNumber,
                    item.role?.mapToWith(roleRealmModelMapper) ?: ChildContactRoleModelRealm()
                    , item.email, item.mobileNumber, item.address?.mapToWith(addressRealmModerMapper) ?: AddressModelRealm(),
                    item.hidePhoneNumber, item.protectedAddress, item.institutionId, false, item.currentPage,
                    item.firstPageUrl, item.from, item.path,
                    item.perPage, item.role.id)
}
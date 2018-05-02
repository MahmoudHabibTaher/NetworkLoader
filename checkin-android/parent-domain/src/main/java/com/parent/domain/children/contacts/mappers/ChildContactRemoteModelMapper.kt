package com.parent.domain.children.contacts.mappers

import com.parent.entities.ChildContactModel
import com.parent.entities.ChildContactModelResponse
import com.parent.entities.ChildContactRelationModelRemote
import com.parent.entities.ChildContactRoleModelRemote
import com.parent.entities.AddressModel
import com.parent.entities.ChildContactRelationModel
import com.parent.entities.ChildContactRoleModel
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.base.mappers.mapToWith
import com.parent.entities.City
import com.parent.entities.Country
import com.parent.entities.CityRemote
import com.parent.entities.CountryRemote

/**
 * Created by mahmoud on 10/3/17.
 */
class ChildContactRemoteModelMapper(var roleRemoteModelMapper: ModelMapper<ChildContactRoleModelRemote, ChildContactRoleModel>,
                                    var relationRemoteModelMapper: ModelMapper<ChildContactRelationModelRemote, ChildContactRelationModel>,
                                    var countryRemoteModelMapper: ModelMapper<CountryRemote, Country>,
                                    var cityRemoteModelMapper: ModelMapper<CityRemote, City>
) : ModelMapper<ChildContactModelResponse, ChildContactModel> {

    override fun mapFrom(item: ChildContactModelResponse): ChildContactModel =
            ChildContactModel(item.id ?: "", item.fullName ?: "", item.photo
                    ?: item.image ?: "",
                    item.relation?.mapFromWith(relationRemoteModelMapper)
                            ?: ChildContactRelationModel(),
                    item.childId ?: "", item.hasLogin ?: item.isLogin ?: "", item.phoneNumber ?: "",
                    item.role?.mapFromWith(roleRemoteModelMapper)
                            ?: ChildContactRoleModel(),
                    item.email ?: "", item.mobileNumber ?: "",
                    AddressModel(item.street ?: "", item.zipCode
                            ?: "", item.country?.mapFromWith(countryRemoteModelMapper) ?: Country(),
                            item.city?.mapFromWith(cityRemoteModelMapper)
                                    ?: City()), item.hidePhoneNumber ?: "",
                    item.protectedAddress ?: "")


    override fun mapTo(item: ChildContactModel): ChildContactModelResponse =
            ChildContactModelResponse(item.id, item.id, item.fullName, item.photo, item.hasLogin, item.hasLogin,
                    item.relation.mapToWith(relationRemoteModelMapper), item.role.mapToWith(roleRemoteModelMapper), item.childId,
                    item.phoneNumber, item.email, item.mobileNumber, item.hidePhoneNumber, item.protectedAddress
                    , item.address.street, item.address.zipCode, item.address.country.mapToWith(countryRemoteModelMapper),
                    item.address.city.mapToWith(cityRemoteModelMapper))
}
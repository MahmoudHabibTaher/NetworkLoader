package com.parent.domain.children.contacts.mappers

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.entities.*
import java.text.ParseException

/**
 * Created by mahmoud on 10/3/17.
 */
class ChildContactResponseRemoteModelMapper(var roleRemoteModelMapper: ModelMapper<ChildContactRoleModelRemote,
        ChildContactRoleModel>, var relationRemoteModelMapper: ModelMapper<ChildContactRelationModelRemote,
        ChildContactRelationModel>, var countryRemoteModelMapper: ModelMapper<CountryRemote, Country>,
                                            var cityRemoteModelMapper: ModelMapper<CityRemote, City>
) : ModelMapper<ChildContactPageRemote, List<ChildContactModel>> {
    override fun mapTo(to: List<ChildContactModel>): ChildContactPageRemote {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mapFrom(response: ChildContactPageRemote): List<ChildContactModel> {

        var list: MutableList<ChildContactModel> = mutableListOf()
        for (item in response.data ?: listOf()) {
            list.add(ChildContactModel(item.id ?: "", item.fullName ?: "", item.photo ?: item.image ?: "",
                    item.relation?.mapFromWith(relationRemoteModelMapper) ?: ChildContactRelationModel(),
                    item.childId ?: "", item.hasLogin ?: item.isLogin ?: "", item.phoneNumber ?: "",
                    item.role?.mapFromWith(roleRemoteModelMapper) ?: ChildContactRoleModel(),
                    item.email ?: "", item.mobileNumber ?: "",
                    AddressModel(item.street ?: "", item.zipCode ?: "",
                            item.country?.mapFromWith(countryRemoteModelMapper) ?: Country(),
                            item.city?.mapFromWith(cityRemoteModelMapper) ?: City()), item.hidePhoneNumber ?: "",
                    item.protectedAddress ?: "", "",mapInt(item.relatedChildren), response.currentPage ?: "",
                    response.firstPageUrl ?: "", response.from ?: "", response.lastPage ?: "",
                    response.lastPageUrl ?: "", response.nextPageUrl ?: "", response.path ?: "",
                    response.perPage ?: ""))
        }
        return list
    }

    fun mapInt(relatedChildren:String?):Int{
        try{
            return relatedChildren?.toInt() ?:0
        }catch (e:ParseException){
            return 0
        }
    }

}
package com.parent.domain.realm.entities

import io.realm.RealmObject

/**
 * Created by Raed Ezzat on 03/01/2018.
 */
open class AddressModelRealm(
        var street: String? = "",
        var zipCode: String? = "",
        var country: CountryRealm? = CountryRealm(),
        var city: CityRealm? = CityRealm()
) : RealmObject()
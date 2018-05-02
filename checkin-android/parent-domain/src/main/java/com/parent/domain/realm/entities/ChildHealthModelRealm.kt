package com.parent.domain.realm.entities

import io.realm.RealmObject

/**
 * Created by Raed Ezzat on 18/12/2017.
 */
open class ChildHealthModelRealm(
        var id: String = "",
        var institutionId: String = "",
        var doctorFullName: String = "",
        var doctorPhoneNumber: String = "",
        var doctorAddress: String = "",
        var doctorZipCode: String = "",
        var doctorStreet: String = "",
        var allergies: String = "",
        var toleratesPenicillin: String = "",
        var specialDietary: String = "",
        var vaccinations: String = "",
        var socialInsuranceNumber: String = "",
        var specialHealthNotes: String = "",
        var city: CityRealm? = CityRealm(),
        var country: CountryRealm? = CountryRealm()
) : RealmObject() {

}
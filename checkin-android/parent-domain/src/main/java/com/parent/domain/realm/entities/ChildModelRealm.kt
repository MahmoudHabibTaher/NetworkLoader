package com.parent.domain.realm.entities

import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by Raed Ezzat on 18/12/2017.
 */
open class ChildModelRealm(
        var id: String = "",
        var fullName: String = "",
        var photo: String = "",
        var gender: String = "",
        var birthplace: String = "",
        var nationality: NationalityRealm? = NationalityRealm(),
        var religion: String = "",
        var preferredLanguage: String = "",
        var otherLanguages: String = "",
        var classModel: ClassRealm? = ClassRealm(),
        var groups: RealmList<GroupRealm> = RealmList(),
        var registerationDate: String = "",
        var lastDate: String = "",
        var specialNotes: String = "",
        var checkinCode: String = "",
        var keyWorker: InstituteStaffModelRealm? = InstituteStaffModelRealm(),
        var emergencyContact: RealmList<ChildContactModelRealm> = RealmList(),
        var livesWith: RealmList<ChildContactModelRealm> = RealmList(),
        var custody: RealmList<ChildContactModelRealm> = RealmList(),
        var personalNumber: String = "",
        var protectedAddress: String = "",
        var street: String = "",
        var zipcode: String = "",
        var country: CountryRealm? = CountryRealm(),
        var city: CityRealm? = CityRealm(),
        var birthdate: String = "",
        var age: String = "",
        var hideStatusFromNonCustody: String = "",
        var skipTags: String = ""
) : RealmObject() {

}
package com.parent.domain.realm.entities

import io.realm.RealmObject

/**
 * Created by mahmoud on 12/4/17.
 */
open class CompanyModelRealm(var id: String = "",
                             var name: String = "",
                             var avatar: String = "",
                             var email: String = "",
                             var contactName: String = "",
                             var address: String = "",
                             var contactTelephone: String = "",
                             var totalChildren: Int = 0,
                             var totalClass: Int = 0,
                             var totalCheckInChildren: Int = 0,
                             var totalStaff: Int = 0,
                             var totalCheckInStaff: Int = 0) : RealmObject()
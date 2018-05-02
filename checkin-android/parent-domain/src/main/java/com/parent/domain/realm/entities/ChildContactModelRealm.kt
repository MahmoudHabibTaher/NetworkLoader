package com.parent.domain.realm.entities

import io.realm.RealmObject

/**
 * Created by Raed Ezzat on 18/12/2017.
 */
open class ChildContactModelRealm(
        var id: String = "",
        var fullName: String = "",
        var photo: String = "",
        var relation: ChildContactRelationModelRealm? = ChildContactRelationModelRealm(),
        var childId: String = "",
        var hasLogin: String = "",
        var phoneNumber: String = "",
        var role: ChildContactRoleModelRealm? = ChildContactRoleModelRealm(),
        var email: String = "",
        var mobileNumber: String = "",
        var address: AddressModelRealm? = AddressModelRealm(),
        var hidePhoneNumber: String = "",
        var protectedAddress: String = "",
        var institutionId: String = "",
        var hasPickup: Boolean = false,
        var currentPage: String = "",
        var firstPageUrl: String = "",
        var from: String = "",
        var path: String = "",
        var perPage: String = "",
        var prevPageUrl: String = "",
        var to: String = "",
        var total: String = "",
        var roleId: String? = ""
) : RealmObject()
package com.parent.entities

/**
 * Created by Raed Ezzat on 18/12/2017.
 */
class ChildContactModel(
        var id: String = "",
        var fullName: String = "",
        var photo: String = "",
        var relation: ChildContactRelationModel = ChildContactRelationModel(),
        var childId: String = "",
        var hasLogin: String = "",
        var phoneNumber: String = "",
        var role: ChildContactRoleModel = ChildContactRoleModel(),
        var email: String = "",
        var mobileNumber: String = "",
        var address: AddressModel = AddressModel(),
        var hidePhoneNumber: String = "",
        var protectedAddress: String = "",
        var institutionId: String = "",
        var relatedChildren: Int = 0,
        var currentPage: String = "",
        var firstPageUrl: String = "",
        var from: String = "",
        var path: String = "",
        var perPage: String = "",
        var prevPageUrl: String = "",
        var to: String = "",
        var total: String = ""
)
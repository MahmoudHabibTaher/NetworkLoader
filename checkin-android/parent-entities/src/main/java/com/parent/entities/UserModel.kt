package com.parent.entities

/**
 * Created by Raed Ezzat on 15/01/2018.
 */
class UserModel(

        var id: String = "",
        var fullName: String = "",
        var textDisplay: String = "",
        var avatar: String = "",
        var isContact: Boolean = false,
        var type: Int = 0,
        var role: Role = Role(),
        var classesList: List<ClassModel> = listOf(),
        var children: List<ChildModel> = listOf(),

        var currentPage: String = "",
        var firstPageUrl: String = "",
        var from: String = "",
        var lastPage: String = "",
        var path: String = "",
        var perPage: String = "",
        var to: String = "",
        var total: String = ""

) {
    companion object {
        const val UNKNOWN = 0
        const val CONTACT = 1
        const val STAFF = 2
    }
}
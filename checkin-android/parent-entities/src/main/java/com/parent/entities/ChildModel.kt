package com.parent.entities

/**
 * Created by Raed Ezzat on 18/12/2017.
 */
class ChildModel(
        var id: String = "",
        var fullName: String = "",
        var photo: String? = "",
        var gender: String = "",
        var birthplace: String = "",
        var nationality: Nationality = Nationality(),
        var religion: String = "",
        var preferredLanguage: String = "",
        var otherLanguages: String = "",
        var classModel: ClassModel = ClassModel(),
        var groups: List<Group> = listOf(),
        var registerationDate: String = "",
        var lastDate: String = "",
        var specialNotes: String = "",
        var checkinCode: String = "",
        var keyWorker: InstituteStaffModel = InstituteStaffModel(),
        var emergencyContact: List<ChildContactModel> = listOf(),
        var livesWith: List<ChildContactModel> = listOf(),
        var custody: List<ChildContactModel> = listOf(),
        var personalNumber: String = "",
        var protectedAddress: String = "",
        var street: String = "",
        var zipcode: String = "",
        var country: Country = Country(),
        var city: City = City(),
        var birthdate: String = "",
        var age: String = "",
        var hideStatusFromNonCustody: String = "",
        var skipTags: String = "",
        var lastStatus: String = "",
        var institutionId: String? = "",

        var currentPage: String = "0",
        var firstPageUrl: String = "",
        var from: String = "",
        var lastPage: String = "0",
        var path: String = "",
        var perPage: String = "",
        var to: String = "",
        var total: String = ""
) {

    companion object {
        const val CHECK_IN_STATUS_TYPE = "CHECK_IN"
        const val CHECK_OUT_STATUS_TYPE = "CHECK_OUT"
    }

}
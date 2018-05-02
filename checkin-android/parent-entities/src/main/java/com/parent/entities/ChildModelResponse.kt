package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Raed Ezzat on 18/12/2017.
 */
class ChildModelResponse(status: String? = "", message: String? = "", data: ChildModelData? = ChildModelData()) : BaseResponse<ChildModelData>(status, message, data)

class ChildModelData(
        @SerializedName("id") var id: String? = "",
        @SerializedName("full_name") var fullName: String? = "",
        @SerializedName("avatar") var photo: String? = "",
        @SerializedName("gender") var gender: String? = "",
        @SerializedName("birthplace") var birthplace: String? = "",
        @SerializedName("nationality") var nationality: NationalityRemote? = NationalityRemote(),
        @SerializedName("religion") var religion: String? = "",
        @SerializedName("preferred_languages") var preferredLanguage: String? = "",
        @SerializedName("other_languages") var otherLanguages: String? = "",
        @SerializedName("class") var classModel: ClassRemote = ClassRemote(),
        @SerializedName("groups") var groups: List<GroupRemote> = listOf(),
        @SerializedName("registeration_date") var registerationDate: String? = "",
        @SerializedName("last_date") var lastDate: String? = "",
        @SerializedName("special_notes") var specialNotes: String? = "",
        @SerializedName("checkin_code") var checkinCode: String? = "",
        @SerializedName("key_worker") var keyWorker: InstituteStaffModelRemote = InstituteStaffModelRemote(),
        @SerializedName("emergency_contact") var emergencyContact: List<ChildContactModelResponse>? = null,
        @SerializedName("lives_with") var livesWith: List<ChildContactModelResponse>? = null,
        @SerializedName("custody") var custody: List<ChildContactModelResponse>? = null,
        @SerializedName("personal_number") var personalNumber: String? = "",
        @SerializedName("protected_address") var protectedAddress: String? = "",
        @SerializedName("street") var street: String? = "",
        @SerializedName("zipcode") var zipcode: String? = "",
        @SerializedName("country") var country: CountryRemote = CountryRemote(),
        @SerializedName("city") var city: CityRemote = CityRemote(),
        @SerializedName("birthdate") var birthdate: String? = "",
        @SerializedName("age") var age: String? = "",
        @SerializedName("hide_status_from_non_custody") var hideStatusFromNonCustody: String? = "",
        @SerializedName("institution_id") var institutionId: String? = "",
        @SerializedName("last_status") var lastStatus: String? = "",
        @SerializedName("Skip_flags") var SkipFlags: String? = ""



) {

}
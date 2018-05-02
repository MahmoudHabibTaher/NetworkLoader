package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Raed Ezzat on 18/12/2017.
 */
class ChildModelRequest(
        @SerializedName("full_name") var fullName: String? = null,
        @SerializedName("avatar") var photo: String? = null,
        @SerializedName("gender") var gender: String? = null,
        @SerializedName("birthplace") var birthplace: String? = null,
        @SerializedName("religion") var religion: String? = null,
        @SerializedName("preferred_languages") var preferredLanguage: String? = null,
        @SerializedName("registeration_date") var registerationDate: String? = null,
        @SerializedName("last_date") var lastDate: String? = null,
        @SerializedName("special_notes") var specialNotes: String? = null,
        @SerializedName("personal_number") var personalNumber: String? = null,
        @SerializedName("protected_address") var protectedAddress: String? = null,
        @SerializedName("street") var street: String? = null,
        @SerializedName("zipcode") var zipcode: String? = null,
        @SerializedName("birthdate") var birthdate: String? = null,
        @SerializedName("hide_status_from_non_custody") var hideStatusFromNonCustody: String? = null,
        @SerializedName("institution_id") var institutionId: String? = null,
        @SerializedName("classroom_id") var classroomId: String? = null,
        @SerializedName("nationality_id") var nationalityId: String? = null,
        @SerializedName("emergency_contact") var emergencyContactIds: List<Int>? = null,
        @SerializedName("other_Languages") var otherLanguagesRequest: String? = null,
        @SerializedName("lives_with") var livesWithIds: List<Int>? = null,
        @SerializedName("keyworker_id") var keyworkerId: String? = null,
        @SerializedName("country_id") var countryId: String? = null,
        @SerializedName("city_name") var cityName: String? = null,
        @SerializedName("custody") var custodyIds: List<Int>? = null
) {

}
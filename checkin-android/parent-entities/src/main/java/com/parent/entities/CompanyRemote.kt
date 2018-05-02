package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 12/4/17.
 */
data class CompanyRemote(@SerializedName("id") val id: String = "",
                         @SerializedName("name") val name: String = "",
                         @SerializedName("avatar") val avatar: String = "",
                         @SerializedName("email") val email: String = "",
                         @SerializedName("contact_name") val contactName: String = "",
                         @SerializedName("address") val address: String = "",
                         @SerializedName("contact_telephone") val contactTelephone: String = "",
                         @SerializedName("total_children") var totalChildren: Int? = 0,
                         @SerializedName("total_class") var totalClass: Int? = 0,
                         @SerializedName("total_checkIn_children") var totalCheckInChildren: Int? = 0,
                         @SerializedName("total_staff") var totalStaff: Int? = 0,
                         @SerializedName("total_checkIn_staff") var totalCheckInStaff: Int? = 0)
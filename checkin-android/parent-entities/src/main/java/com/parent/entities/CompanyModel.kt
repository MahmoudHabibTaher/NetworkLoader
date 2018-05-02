package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 12/4/17.
 */
data class CompanyModel(val id: String = "",
                        val name: String = "",
                        val avatar: String = "",
                        val email: String = "",
                        val contactName: String = "",
                        val address: String = "",
                        val contactTelephone: String = "",
                        var totalChildren: Int = 0,
                        var totalClass: Int = 0,
                        var totalCheckInChildren: Int = 0,
                        var totalStaff: Int = 0,
                        var totalCheckInStaff: Int = 0)
package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 10/23/17.
 */
data class RoleRemote(@SerializedName("id") val id: String? = "",
                      @SerializedName("name") val name: String? = "",
                      @SerializedName("usersCount") val usersCount: Int? = 0,
                      @SerializedName("company_id") val companyId: String? = "",
                      @SerializedName("institution_id") val institutionId: String? = "",
                      @SerializedName("permissions") val permissions: List<PermissionRemote>? = listOf(),
                      @SerializedName("users") val users: List<RoleUserRemote>? = listOf()) {
    class TestBuilder : IBuilder<RoleRemote> {
        companion object {
            private val IDS = listOf("1", "2", "3", "4")
            private val NAMES = listOf("Company Admin", "Teacher", "Nutritionist", "Institute Admin")
            private val USERS_COUNTS = listOf(1, 35, 2, 5)
            private val COMPANY_IDS = listOf("1", "1", "1", "1")
            private val INSTITUTION_IDS = listOf("", "1", "1", "")

            fun buildValidRemoteRole() =
                    TestBuilder().id("1")
                            .name("Company Admin")
                            .usersCount(1)
                            .companyId("1")
                            .institutionId("1")
                            .build()

            fun buildCompanyRole() =
                    TestBuilder().id("1")
                            .name("Company Admin")
                            .usersCount(1)
                            .companyId("1")
                            .institutionId(null)
                            .build()

            fun buildInstituteRole() =
                    TestBuilder().id("1")
                            .name("Teacher")
                            .usersCount(35)
                            .companyId("1")
                            .institutionId("2F")
                            .build()

            fun buildList(): List<RoleRemote> =
                    IDS.mapIndexed { index, id ->
                        TestBuilder().id(id).name(NAMES[index]).usersCount(USERS_COUNTS[index])
                                .companyId(COMPANY_IDS[index]).institutionId(INSTITUTION_IDS[index])
                                .build()
                    }
        }

        private var id: String? = ""
        private var name: String? = ""
        private var usersCount: Int? = 0
        private var companyId: String? = ""
        private var institutionId: String? = ""

        fun id(id: String?): TestBuilder {
            this.id = id
            return this
        }

        fun name(name: String?): TestBuilder {
            this.name = name
            return this
        }

        fun usersCount(count: Int?): TestBuilder {
            this.usersCount = count
            return this
        }

        fun companyId(id: String?): TestBuilder {
            this.companyId = id
            return this
        }

        fun institutionId(id: String?): TestBuilder {
            this.institutionId = id
            return this
        }

        override fun build(): RoleRemote =
                RoleRemote(id, name, usersCount, companyId, institutionId)
    }
}
package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 10/23/17.
 */
data class RoleRemoteSection(@SerializedName("name") val name: String = "",
                             @SerializedName("roles") val roles: List<RoleRemote> = listOf()) {
    class TestBuilder : IBuilder<RoleRemoteSection> {
        companion object {
            private val testNames = listOf("Company", "Institute 1", "Institute 2")

            fun buildList() =
                    testNames.map { TestBuilder().name(it).roles(RoleRemote.TestBuilder.buildList()).build() }
        }

        private var name: String = ""
        private var roles: List<RoleRemote> = listOf()

        fun name(name: String): TestBuilder {
            this.name = name
            return this
        }

        fun roles(roles: List<RoleRemote>): TestBuilder {
            this.roles = roles
            return this
        }

        override fun build(): RoleRemoteSection =
                RoleRemoteSection(name, roles)
    }
}
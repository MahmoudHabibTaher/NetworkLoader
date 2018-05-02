package com.parent.entities

/**
 * Created by mahmoud on 10/23/17.
 */
data class RoleSection(val name: String = "", val roles: List<Role> = listOf()) {
    class Builder : IBuilder<RoleSection> {
        private var name: String = ""
        private var roles: List<Role> = listOf()

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun roles(roles: List<Role>): Builder {
            this.roles = roles
            return this
        }

        override fun build(): RoleSection =
                RoleSection(name, roles)
    }

    class TestBuilder : IBuilder<RoleSection> {
        companion object {
            val NAMES = listOf("Company Roles", "Institute Roles")
            fun buildList(): List<RoleSection> = NAMES.map { TestBuilder().name(it).roles(Role.TestBuilder.buildList()).build() }
        }

        private val builder = Builder()

        fun name(name: String): Builder = builder.name(name)

        fun roles(roles: List<Role>): Builder = builder.roles(roles)

        override fun build(): RoleSection =
                builder.build()
    }
}
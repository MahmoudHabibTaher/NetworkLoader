package com.parent.entities

/**
 * Created by mahmoud on 9/19/17.
 */
data class Role(var id: String = "",
                var name: String = "",
                var companyId: String = "",
                var instituteId: String = "",
                var usersCount: Int = 0,
                var level: Int = 0,
                var permissions: List<Permission> = listOf(),
                var users: List<RoleUser> = listOf()) {
    object Level {
        const val COMPANY = 0
        const val INSTITUTE = 1
    }

    class Builder : IBuilder<Role> {
        private var id: String = ""
        private var name: String = ""
        private var companyId: String = ""
        private var instituteId: String = ""
        private var usersCount: Int = 0
        private var level: Int = 0
        private var permissions: List<Permission> = listOf()
        private var users: List<RoleUser> = listOf()

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun companyId(companyId: String): Builder {
            this.companyId = companyId
            return this
        }

        fun instituteId(instituteId: String): Builder {
            this.instituteId = instituteId
            return this
        }

        fun usersCount(count: Int): Builder {
            this.usersCount = count
            return this
        }

        fun level(level: Int): Builder {
            this.level = level
            return this
        }

        fun permissions(permissions: List<Permission>): Builder {
            this.permissions = permissions
            return this
        }

        fun users(users: List<RoleUser>): Builder {
            this.users = users
            return this
        }

        override fun build(): Role =
                Role(id, name, companyId, instituteId, usersCount, level, permissions, users)
    }

    class TestBuilder {
        companion object {
            private val names = listOf("Company Admin", "Teacher")
            private val ids = listOf("1", "2")
            private val companyIds = listOf("1", "1")
            private val instituteIds = listOf("", "2")
            private val usersCount = listOf(2, 33)
            private val levels = listOf(Level.COMPANY, Level.INSTITUTE)
            private val permissions = listOf<Permission>()
            private val users = listOf<RoleUser>()

            fun buildNormalRole() =
                    Builder().id("1")
                            .name("Copmany Admin")
                            .companyId("1")
                            .instituteId("1")
                            .usersCount(33)
                            .level(Level.COMPANY)
                            .permissions(Permission.TestBuilder.buildList())
                            .users(RoleUser.TestBuilder.buildList())
                            .build()

            fun buildInstituteRole() =
                    Builder().id("1")
                            .name("Copmany Admin")
                            .companyId("1")
                            .instituteId("1")
                            .usersCount(33)
                            .level(Level.INSTITUTE)
                            .permissions(Permission.TestBuilder.buildList())
                            .users(RoleUser.TestBuilder.buildList())
                            .build()

            fun buildRoleWithNoUsers() =
                    Builder().id("1")
                            .name("Copmany Admin")
                            .companyId("1")
                            .instituteId("1")
                            .usersCount(33)
                            .level(Level.COMPANY)
                            .permissions(Permission.TestBuilder.buildList())
                            .users(listOf())
                            .build()

            fun buildList() = ids.mapIndexed { index, id ->
                Builder().id(id)
                        .name(names[index])
                        .companyId(companyIds[index])
                        .instituteId(instituteIds[index])
                        .usersCount(usersCount[index])
                        .level(levels[index])
                        .permissions(permissions)
                        .users(users)
                        .build()
            }
        }
    }
}
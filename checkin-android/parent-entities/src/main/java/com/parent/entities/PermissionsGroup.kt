package com.parent.entities

/**
 * Created by mahmoud on 9/26/17.
 */
data class PermissionsGroup(var id: String = "",
                            var name: String = "",
                            var permissions: List<Permission> = listOf()) {
    class Builder : IBuilder<PermissionsGroup> {
        private var id: String = ""
        private var name: String = ""
        private var permissions: List<Permission> = listOf()

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun permissions(permissions: List<Permission>): Builder {
            this.permissions = permissions
            return this
        }

        override fun build(): PermissionsGroup =
                PermissionsGroup(id, name, permissions)
    }

    class TestBuilder {
        companion object {
            fun buildList(): List<PermissionsGroup> =
                    listOf(PermissionsGroup("1", "PermissionGroup", Permission.TestBuilder.buildList()))
        }
    }
}
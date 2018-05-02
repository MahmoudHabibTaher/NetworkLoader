package com.parent.entities

/**
 * Created by mahmoud on 9/26/17.
 */
data class PermissionGroupRemote(val id: String = "", val name: String = "",
                                 val permissions: List<PermissionRemote> = listOf()) {
    class Builder : IBuilder<PermissionGroupRemote> {
        private var id: String = ""
        private var name: String = ""
        private var permissions: List<PermissionRemote> = listOf()

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun permissions(permissions: List<PermissionRemote>): Builder {
            this.permissions = permissions
            return this
        }

        override fun build(): PermissionGroupRemote =
                PermissionGroupRemote(id, name, permissions)
    }

    class TestBuilder {
        companion object {
            private val idList = listOf("1", "2")
            private val nameList = listOf("Group1", "Group2")

            private val builder = Builder()

            fun buildList(): List<PermissionGroupRemote> =
                    idList.mapIndexed { index, id ->
                        builder.id(id)
                                .name(nameList[index])
                                .permissions(PermissionRemote.TestBuilder.buildList())
                                .build()
                    }
        }
    }
}
package com.parent.entities

/**
 * Created by mahmoud on 11/13/17.
 */
data class RoleUser(val id: String = "",
                    val email: String = "",
                    val name: String = "",
                    val avatar: String = "",
                    val classes: List<ClassModel> = listOf()) {
    class Builder : IBuilder<RoleUser> {
        private var id = ""
        private var email = ""
        private var name = ""
        private var avatar = ""
        private var classes = listOf<ClassModel>()

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun email(email: String): Builder {
            this.email = email
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun avatar(avatar: String): Builder {
            this.avatar = avatar
            return this
        }

        fun classes(classes: List<ClassModel>): Builder {
            this.classes = classes
            return this
        }

        override fun build(): RoleUser =
                RoleUser(id, email, name, avatar, classes)
    }

    class TestBuilder {
        companion object {
            fun buildList() =
                    listOf(buildNormalUser())

            fun buildNormalUser() =
                    Builder().id("1")
                            .name("Habib")
                            .email("habib@parent.eu")
                            .avatar("avatar")
                            .classes(ClassModel.TestBuilder.buildList())
                            .build()
        }
    }
}
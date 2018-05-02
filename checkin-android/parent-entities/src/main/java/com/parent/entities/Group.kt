package com.parent.entities

/**
 * Created by mahmoud on 10/17/17.
 */
data class Group(val id: String = "",
                 val name: String = "",
                 val avatar: String = "") {
    class Builder : IBuilder<Group> {
        private var id = ""
        private var name = ""
        private var avatar = ""

        fun id(id: String): Builder {
            this.id = id
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

        override fun build(): Group =
                Group(id, name, avatar)
    }

    class TestBuilder {
        companion object {
            fun buildNormalClass() =
                    Builder().id("1")
                            .name("Dolphins")
                            .avatar("avatar")
                            .build()

            fun buildList() =
                    listOf(buildNormalClass())
        }
    }
}
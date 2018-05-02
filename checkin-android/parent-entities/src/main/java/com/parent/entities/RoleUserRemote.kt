package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 11/13/17.
 */
data class RoleUserRemote(@SerializedName("id") val id: String? = "",
                          @SerializedName("name") val name: String? = "",
                          @SerializedName("email") val email: String? = "",
                          @SerializedName("avatar") val avatar: String? = "",
                          @SerializedName("classes") val classes: List<ClassRemote>? = listOf()) {
    class Builder : IBuilder<RoleUserRemote> {
        private var id: String? = ""
        private var name: String? = ""
        private var email: String? = ""
        private var avatar: String? = ""
        private var classes: List<ClassRemote>? = listOf()

        fun id(id: String?): Builder {
            this.id = id
            return this
        }

        fun name(name: String?): Builder {
            this.name = name
            return this
        }

        fun email(email: String?): Builder {
            this.email = email
            return this
        }

        fun avatar(avatar: String?): Builder {
            this.avatar = avatar
            return this
        }

        fun classes(classes: List<ClassRemote>?): Builder {
            this.classes = classes
            return this
        }

        override fun build(): RoleUserRemote =
                RoleUserRemote(id, name, email, avatar, classes)
    }

    class TestBuilder {
        companion object {
            fun buildValidRoleUserRemote() =
                    Builder().id("1")
                            .name("Mahmoud Habib")
                            .email("habib@parent.eu")
                            .avatar("user_avatar")
                            .classes(ClassRemote.TestBuilder.buildList())
                            .build()

            fun buildEmptyRoleUserRemote() =
                    Builder().id(null)
                            .name(null)
                            .email(null)
                            .avatar(null)
                            .classes(null)
                            .build()
        }
    }
}
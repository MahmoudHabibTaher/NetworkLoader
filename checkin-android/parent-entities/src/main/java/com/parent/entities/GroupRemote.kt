package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 10/17/17.
 */
class GroupRemote(@SerializedName("id") val id: String? = "",
                  @SerializedName("name") val name: String? = "",
                  @SerializedName("avatar") val avatar: String? = "") {
    class Builder : IBuilder<GroupRemote> {
        private var id: String? = ""
        private var name: String? = ""
        private var avatar: String? = ""

        fun id(id: String?): Builder {
            this.id = id
            return this
        }

        fun name(name: String?): Builder {
            this.name = name
            return this
        }

        fun avatar(avatar: String?): Builder {
            this.avatar = avatar
            return this
        }

        override fun build(): GroupRemote =
                GroupRemote(id, name, avatar)
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
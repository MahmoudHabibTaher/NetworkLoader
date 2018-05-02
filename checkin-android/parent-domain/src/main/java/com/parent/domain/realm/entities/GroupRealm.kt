package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import io.realm.RealmObject

/**
 * Created by mahmoud on 10/17/17.
 */
open class GroupRealm(var id: String? = "",
                      var name: String? = "",
                      var avatar: String? = "") : RealmObject() {
    class Builder : IBuilder<GroupRealm> {
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

        override fun build(): GroupRealm =
                GroupRealm(id, name, avatar)
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
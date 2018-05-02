package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import io.realm.RealmObject

/**
 * Created by mahmoud on 11/18/17.
 */
open class RecipientRealm(
        var id: String = "",
        var name: String = "",
        var type: String = "",
        var avatar: String = "",
        var description: String = "",
        var extraDescription: String = "") : RealmObject() {
    class Builder : IBuilder<RecipientRealm> {
        private var id = ""
        private var name = ""
        private var type = ""
        private var avatar = ""
        private var description = ""
        private var extraDescription = ""

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun type(type: String): Builder {
            this.type = type
            return this
        }

        fun avatar(avatar: String): Builder {
            this.avatar = avatar
            return this
        }

        fun description(description: String): Builder {
            this.description = description
            return this
        }

        fun extraDescription(description: String): Builder {
            this.extraDescription = description
            return this
        }

        override fun build(): RecipientRealm =
                RecipientRealm(id, name, type, avatar, description, extraDescription)
    }

    class TestBuilder {
        companion object {
            fun buildNormalRecipient() =
                    Builder().id("1")
                            .name("Mahmoud Habib")
                            .type("Parent")
                            .avatar("user_avatar")
                            .build()

            fun buildList() = listOf(buildNormalRecipient())
        }
    }
}
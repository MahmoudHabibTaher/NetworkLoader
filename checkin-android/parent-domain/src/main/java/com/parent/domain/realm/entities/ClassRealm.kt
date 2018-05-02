package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import com.parent.domain.realm.base.toRealmList
import io.realm.RealmObject

/**
 * Created by mahmoud on 11/13/17.
 */
open class ClassRealm(var id: String = "",
                      var name: String = "",
                      var avatar: String = "",
                      var instituteId: String = "") : RealmObject() {
    class Builder : IBuilder<ClassRealm> {
        private var id = ""
        private var name = ""
        private var avatar = ""
        private var instituteId = ""

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

        fun instituteId(id: String): Builder {
            this.instituteId = id
            return this
        }

        override fun build(): ClassRealm =
                ClassRealm(id, name, avatar, instituteId)
    }

    class TestBuilder {
        companion object {
            fun buildNormalClass() =
                    Builder()
                            .id("1")
                            .name("Dolphins")
                            .avatar("avatar")
                            .instituteId("2")
                            .build()

            fun buildList() =
                    listOf(buildNormalClass()).toRealmList()
        }
    }
}
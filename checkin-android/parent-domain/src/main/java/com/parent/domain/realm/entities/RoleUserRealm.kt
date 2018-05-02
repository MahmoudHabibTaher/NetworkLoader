package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import com.parent.domain.realm.base.toRealmList
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by mahmoud on 11/13/17.
 */
open class RoleUserRealm(@PrimaryKey var id: String = "",
                         var name: String = "",
                         var email: String = "",
                         var avatar: String = "",
                         var classes: RealmList<ClassRealm> = RealmList()) : RealmObject() {
    class Builder : IBuilder<RoleUserRealm> {
        private var id = ""
        private var name = ""
        private var email = ""
        private var avatar = ""
        private var classes = RealmList<ClassRealm>()

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun email(email: String): Builder {
            this.email = email
            return this
        }

        fun avatar(avatar: String): Builder {
            this.avatar = avatar
            return this
        }

        fun classes(classes: RealmList<ClassRealm>): Builder {
            this.classes = classes
            return this
        }

        override fun build(): RoleUserRealm =
                RoleUserRealm(id, name, email, avatar, classes)
    }

    class TestBuilder {
        companion object {
            fun buildNormalUser() =
                    Builder().id("1")
                            .name("Mahmoud Habib")
                            .email("habib@parent.eu")
                            .avatar("user_avatar")
                            .classes(ClassRealm.TestBuilder.buildList())
                            .build()

            fun buildList() =
                    listOf(buildNormalUser()).toRealmList()
        }
    }
}
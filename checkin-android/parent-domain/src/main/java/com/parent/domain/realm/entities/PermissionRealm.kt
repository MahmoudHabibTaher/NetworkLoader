package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by mahmoud on 9/19/17.
 */
open class PermissionRealm(@PrimaryKey var id: String = "",
                           var name: String = "",
                           var code: String = "") : RealmObject() {
    class Builder : IBuilder<PermissionRealm> {
        private var id: String = ""
        private var name: String = ""
        private var code: String = ""

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun code(code: String): Builder {
            this.code = code
            return this
        }

        override fun build(): PermissionRealm =
                PermissionRealm(id, name, code)
    }

    class TestBuilder {
        companion object {
            fun buildNormalPermission() =
                    Builder().id("1")
                            .name("Add Role")
                            .code("add_role")
                            .build()

            fun buildList(seed: Int): RealmList<PermissionRealm> {
                val list = RealmList<PermissionRealm>()
                for (i in 1..4) {
                    val id = "${seed + i}"
                    val name = "Permission $id"
                    val code = "Code $id"
                    list.add(Builder().id(id).name(name).code(code).build())
                }
                return list
            }
        }
    }
}
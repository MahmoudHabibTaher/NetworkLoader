package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by mahmoud on 9/26/17.
 */
open class PermissionsGroupRealm(@PrimaryKey var id: String = "",
                                 var name: String = "",
                                 var permissions: RealmList<PermissionRealm> = RealmList()) : RealmObject() {
    class Builder : IBuilder<PermissionsGroupRealm> {
        private var id = ""
        private var name = ""
        private var permissions = RealmList<PermissionRealm>()

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun permissions(permissions: RealmList<PermissionRealm>): Builder {
            this.permissions = permissions
            return this
        }

        override fun build(): PermissionsGroupRealm =
                PermissionsGroupRealm(id, name, permissions)
    }

    class TestBuilder {
        companion object {
            fun buildList(): List<PermissionsGroupRealm> {
                val list = mutableListOf<PermissionsGroupRealm>()
                for (i in 1..4) {
                    val id = "$i"
                    val name = "Group $i"
                    list.add(Builder().id(id).name(name).permissions(PermissionRealm.TestBuilder.buildList(i)).build())
                }
                return list
            }
        }
    }
}
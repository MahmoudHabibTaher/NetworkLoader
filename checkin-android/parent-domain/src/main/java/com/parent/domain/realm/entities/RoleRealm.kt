package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by mahmoud on 10/24/17.
 */
open class RoleRealm(@PrimaryKey var id: String = "",
                     var name: String = "",
                     var companyId: String = "",
                     var institutionId: String = "",
                     var usersCount: Int = 0,
                     var users: RealmList<RoleUserRealm> = RealmList(),
                     var permissions: RealmList<PermissionRealm> = RealmList()) : RealmObject() {
    class Builder : IBuilder<RoleRealm> {
        private var id = ""
        private var name = ""
        private var companyId = ""
        private var institutionId = ""
        private var usersCount = 0
        private var users = RealmList<RoleUserRealm>()
        private var permissions = RealmList<PermissionRealm>()

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun companyId(id: String): Builder {
            this.companyId = id
            return this
        }

        fun institutionId(id: String): Builder {
            this.institutionId = id
            return this
        }

        fun usersCount(count: Int): Builder {
            this.usersCount = count
            return this
        }

        fun users(users: RealmList<RoleUserRealm>): Builder {
            this.users = users
            return this
        }

        fun permissions(permissions: RealmList<PermissionRealm>): Builder {
            this.permissions = permissions
            return this
        }

        override fun build(): RoleRealm =
                RoleRealm(id, name, companyId, institutionId, usersCount, users, permissions)
    }

    class TestBuilder {
        companion object {
            fun buildRoleWithNoUsers() =
                    Builder()
                            .id("1")
                            .name("Company Admin")
                            .companyId("1")
                            .institutionId("1")
                            .usersCount(0)
                            .users(RealmList())
                            .permissions(PermissionRealm.TestBuilder.buildList(1))
                            .build()
        }
    }
}
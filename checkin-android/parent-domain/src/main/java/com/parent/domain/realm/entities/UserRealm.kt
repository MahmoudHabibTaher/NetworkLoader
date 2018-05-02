package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import com.parent.domain.realm.base.toRealmList
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by mahmoud on 10/5/17.
 */
open class UserRealm(@PrimaryKey var id: String = "",
                     var username: String = "",
                     var fullName: String = "",
                     var email: String = "",
                     var mobile: String = "",
                     var phone: String = "",
                     var isContact: Boolean = false,
                     var isStaff: Boolean = false,
                     var token: String = "",
                     var refreshToken: String = "",
                     var accountTypeId: Int = -1,
                     var avatar: String = "",
                     var institutions: RealmList<InstitutionModelRealm> = RealmList(),
                     var company: CompanyModelRealm? = CompanyModelRealm(),
                     var children: RealmList<ChildModelRealm> = RealmList(),
                     var role: RoleRealm? = RoleRealm(),
                     var permissions: RealmList<PermissionRealm> = RealmList()
) : RealmObject() {
    class Builder : IBuilder<UserRealm> {
        private var id = ""
        private var username = ""
        private var name = ""
        private var email = ""
        private var token = ""
        private var refreshToken = ""
        private var accountTypeId = -1
        private var avatar = ""
        private var institutions: RealmList<InstitutionModelRealm> = RealmList()
        private var role = RoleRealm()
        private var permissions = RealmList<PermissionRealm>()
        private var isContact: Boolean = false
        private var isStaff: Boolean = false
        private var mobile: String = ""
        private var phone: String = ""
        private var company: CompanyModelRealm = CompanyModelRealm()
        private var children: RealmList<ChildModelRealm> = RealmList()

        fun withId(id: String): Builder {
            this.id = id
            return this
        }

        fun withUsername(username: String): Builder {
            this.username = username
            return this
        }

        fun withName(name: String): Builder {
            this.name = name
            return this
        }

        fun withEmail(email: String): Builder {
            this.email = email
            return this
        }

        fun withIsContact(isContact: Boolean): Builder {
            this.isContact = isContact
            return this
        }

        fun withIsStaff(isStaff: Boolean): Builder {
            this.isStaff = isStaff
            return this
        }

        fun withToken(token: String): Builder {
            this.token = token
            return this
        }

        fun withRefreshToken(refreshToken: String): Builder {
            this.refreshToken = refreshToken
            return this
        }

        fun withAccountTypeId(accountTypeId: Int): Builder {
            this.accountTypeId = accountTypeId
            return this
        }

        fun withAvatar(avatar: String): Builder {
            this.avatar = avatar
            return this
        }

        fun withRoles(role: RoleRealm): Builder {
            this.role = role
            return this
        }

        fun withInstitutions(institutions: RealmList<InstitutionModelRealm>): Builder {
            this.institutions = institutions
            return this
        }

        fun withPermissions(permissions: RealmList<PermissionRealm>): Builder {
            this.permissions = permissions
            return this
        }

        fun mobile(mobile: String): Builder {
            this.mobile = mobile
            return this
        }

        fun phone(phone: String): Builder {
            this.phone = phone
            return this
        }

        fun company(company: CompanyModelRealm): Builder {
            this.company = company
            return this
        }

        fun children(children: RealmList<ChildModelRealm>): Builder {
            this.children = children
            return this
        }

        override fun build(): UserRealm = UserRealm(id, username, name, email, mobile, phone, isContact, isStaff,
                token, refreshToken, accountTypeId, avatar, institutions, company, children, role, permissions)
    }

    class TestBuilder(private var builder: Builder = Builder()) : IBuilder<UserRealm> {
        var id = ""
        var username = ""
        var name = ""
        var email = ""
        var token = ""
        var refreshToken = ""
        var accountTypeId = -1
        var avatar = ""
        var role = RoleRealm()
        var institutions = RealmList<InstitutionModelRealm>()
        var permissions = RealmList<PermissionRealm>()

        fun buildNormalUser(): UserRealm {
            id = "user_id"
            username = "mahmoud.habib"
            name = "Habib"
            email = "habib@parent.eu"
            token = "access_token"
            refreshToken = "refresh_token"
            accountTypeId = 1
            avatar = "avatar"
            role = RoleRealm()
            institutions = RealmList()
            permissions = RealmList()
            return build()
        }

        fun buildList() =
                listOf(buildNormalUser()).toRealmList()

        override fun build(): UserRealm =
                builder.withId(id)
                        .withUsername(username)
                        .withName(name)
                        .withEmail(email)
                        .withToken(token)
                        .withRefreshToken(refreshToken)
                        .withAccountTypeId(accountTypeId)
                        .withAvatar(avatar)
                        .withRoles(role)
                        .withInstitutions(institutions)
                        .withPermissions(permissions)
                        .build()
    }
}
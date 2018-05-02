package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 10/3/17.
 */
data class UserRemote(@SerializedName("id") var id: String? = "",
                      @SerializedName("username") var username: String? = "",
                      @SerializedName("full_name") var fullName: String? = "",
                      @SerializedName("email") var email: String? = "",
                      @SerializedName("account_type_id") var accountTypeId: Int? = -1,
                      @SerializedName("mobile") var mobile: String? = "",
                      @SerializedName("phone") var phone: String? = "",
                      @SerializedName("avatar") var avatar: String? = "",
                      @SerializedName("role") var role: RoleRemote? = null,
                      @SerializedName("institutions") var institutions: List<InstitutionModelRemote>? = listOf(),
                      @SerializedName("company") var company: CompanyRemote? = CompanyRemote(),
                      @SerializedName("classes") var classes: List<ClassRemote>? = listOf(),
                      @SerializedName("children") var children: List<ChildModelData>? = listOf(),
                      @SerializedName("is_contact") var isContact: String? = "",
                      @SerializedName("is_staff") var isStaff: String? = "",
                      @SerializedName("language") var language: LanguageRemote? = null

                      ) {
    class Builder : IBuilder<UserRemote> {


        private var id: String? = ""
        private var roleId: String? = ""
        private var companyId: String? = ""
        private var languageId: String? = ""
        private var mobile: String? = ""
        private var phone: String? = ""
        private var birthdate: String? = ""
        private var company: CompanyRemote? = CompanyRemote()
        private var isContact: String? = ""
        private var isStaff: String? = ""
        private var username: String? = ""
        private var email: String? = ""
        private var accountTypeId: Int? = -1
        private var role: RoleRemote? = RoleRemote()
        private var name: String? = ""
        private var avatar: String? = ""
        private var institutions: List<InstitutionModelRemote>? = listOf()
        private var classes: List<ClassRemote>? = listOf()
        private var children: List<ChildModelData>? = listOf()
        var language: LanguageRemote? = LanguageRemote()
        fun id(id: String?): Builder {
            this.id = id
            return this
        }

        fun username(username: String?): Builder {
            this.username = username
            return this
        }

        fun email(email: String?): Builder {
            this.email = email
            return this
        }

        fun accountTypeId(accountTypeId: Int?): Builder {
            this.accountTypeId = accountTypeId
            return this
        }

        fun role(role: RoleRemote?): Builder {
            this.role = role
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

        fun classes(classes: List<ClassRemote>?): Builder {
            this.classes = classes
            return this
        }

        fun institutions(institutions: List<InstitutionModelRemote>?): Builder {
            this.institutions = institutions
            return this
        }

        fun roleId(roleId: String?): Builder {
            this.roleId = roleId
            return this
        }

        fun companyId(companyId: String?): Builder {
            this.companyId = companyId
            return this
        }

        fun languageId(languageId: String?): Builder {
            this.languageId = languageId
            return this
        }

        fun mobile(mobile: String?): Builder {
            this.mobile = mobile
            return this
        }

        fun phone(phone: String?): Builder {
            this.phone = phone
            return this
        }

        fun birthdate(birthdate: String?): Builder {
            this.birthdate = birthdate
            return this
        }

        fun company(company: CompanyRemote?): Builder {
            this.company = company
            return this
        }

        fun isContact(isContact: String?): Builder {
            this.isContact = isContact
            return this
        }

        fun isStaff(isStaff: String?): Builder {
            this.isStaff = isStaff
            return this
        }

        fun children(children: List<ChildModelData>?): Builder {
            this.children = children
            return this
        }

        fun language(language: LanguageRemote?): Builder {
            this.language = language
            return this
        }


        override fun build(): UserRemote =
                UserRemote(id,
                        username,
                        name,
                        email,
                        accountTypeId,
                        mobile,
                        phone,
                        avatar,
                        role,
                        institutions,
                        company,
                        classes,
                        children,
                        isContact,
                        isStaff,
                        language)
    }

    class TestBuilder {
        companion object {
            fun buildNormalUser() =
                    Builder().id("1")
                            .username("habib")
                            .email("habib@parent.eu")
                            .accountTypeId(1)
                            .role(RoleRemote.TestBuilder.buildValidRemoteRole())
                            .name("Mahmoud Habib")
                            .avatar("avatar")
                            .language(LanguageRemote())
                            .institutions(listOf(InstitutionModelRemote.TestBuilder.buildValidInstitution()))
                            .classes(ClassRemote.TestBuilder.buildList())
                            .build()
        }
    }
}
package com.parent.entities

/**
 * Created by mahmoud on 10/2/17.
 */
data class User(var id: String = "",
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
                var role: Role = Role(),
                var institutions: List<InstitutionModel> = listOf(),
                var company: CompanyModel = CompanyModel(),
                var children: List<ChildModel> = listOf(),
                var classes: List<ClassModel> = listOf(),
                var language: Language = Language(),
                var institutionId: String = "",
                var cityName: String = "",
                var countryId: String = "",
                var workNumber: String = "",
                var street: String = "",
                var zipCode: String = "") {
    class Builder : IBuilder<User> {
        private var id = ""
        private var username = ""
        private var name = ""
        private var email = ""
        private var token = ""
        private var refreshToken = ""
        private var accountTypeId = -1
        private var role = Role()
        private var avatar = ""
        private var institutions: List<InstitutionModel> = listOf()
        private var classes = listOf<ClassModel>()
        private var isContact: Boolean = false
        private var isStaff: Boolean = false
        private var mobile: String = ""
        private var phone: String = ""
        private var company: CompanyModel = CompanyModel()
        var children: List<ChildModel> = listOf()
        var language: Language = Language()
        private var institutionId: String = ""
        private var cityName: String = ""
        private var countryId: String = ""
        private var workNumber: String = ""
        private var street: String = ""
        private var zipCode: String = ""

        fun institutionId(institutionId: String): Builder {
            this.institutionId = institutionId
            return this
        }

        fun cityName(cityName: String): Builder {
            this.cityName = cityName
            return this
        }

        fun countryId(countryId: String): Builder {
            this.countryId = countryId
            return this
        }

        fun workNumber(workNumber: String): Builder {
            this.workNumber = workNumber
            return this
        }

        fun street(street: String): Builder {
            this.street = street
            return this
        }

        fun zipCode(zipCode: String): Builder {
            this.zipCode = zipCode
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

        fun company(company: CompanyModel): Builder {
            this.company = company
            return this
        }


        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun username(username: String): Builder {
            this.username = username
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

        fun isContact(isContact: Boolean): Builder {
            this.isContact = isContact
            return this
        }

        fun isStaff(isStaff: Boolean): Builder {
            this.isStaff = isStaff
            return this
        }

        fun token(token: String): Builder {
            this.token = token
            return this
        }

        fun refreshToken(token: String): Builder {
            this.refreshToken = token
            return this
        }

        fun accountTypeId(id: Int): Builder {
            this.accountTypeId = id
            return this
        }

        fun role(role: Role): Builder {
            this.role = role
            return this
        }

        fun avatar(avatar: String): Builder {
            this.avatar = avatar
            return this
        }

        fun institutions(institutions: List<InstitutionModel>): Builder {
            this.institutions = institutions
            return this
        }

        fun classes(classes: List<ClassModel>): Builder {
            this.classes = classes
            return this
        }

        fun children(children: List<ChildModel>): Builder {
            this.children = children
            return this
        }

        fun language(language: Language): Builder {
            this.language = language
            return this
        }

        override fun build(): User =
                User(id, username, name, email, mobile, phone, isContact, isStaff, token, refreshToken, accountTypeId, avatar,
                        role, institutions, company, children, classes, language, institutionId, cityName, countryId, workNumber, street, zipCode)
    }

    class TestBuilder {
        companion object {
            fun buildList() =
                    listOf(buildNormalUserNoRole())

            fun buildNormalUser() =
                    Builder().id("1")
                            .username("habib@parent.eu")
                            .name("Habib")
                            .email("habib@parent.eu")
                            .isContact(false)
                            .isStaff(false)
                            .token("access_token")
                            .refreshToken("refresh_token")
                            .accountTypeId(-1)
                            .role(Role.TestBuilder.buildNormalRole())
                            .avatar("avatar")
                            .institutions(listOf(InstitutionModel.TestBuilder.buildValidInstitution()))
                            .classes(ClassModel.TestBuilder.buildList())
                            .language(Language())
                            .build()

            fun buildNormalUserNoRole() =
                    Builder().id("1")
                            .username("habib@parent.eu")
                            .name("Habib")
                            .email("habib@parent.eu")
                            .isContact(false)
                            .isStaff(false)
                            .token("access_token")
                            .refreshToken("refresh_token")
                            .accountTypeId(-1)
                            .avatar("http://www.mulierchile.com/profile-pictures/profile-pictures-009.jpg")
                            .institutions(listOf(InstitutionModel.TestBuilder.buildValidInstitution()))
                            .classes(ClassModel.TestBuilder.buildList())
                            .language(Language())
                            .build()
        }
    }
}
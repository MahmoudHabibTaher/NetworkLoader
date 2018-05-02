package com.parent.domain.user

import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.CompanyModel
import com.parent.entities.LoginData
import com.parent.entities.User
import com.parent.entities.UserRemote

/**
 * Created by mahmoud on 10/3/17.
 */
class LoginDataModelMapper(private val userModelMapper: ModelMapper<UserRemote, User>) : ModelMapper<LoginData, User> {
    override fun mapFrom(from: LoginData): User =
            User.Builder().apply {
                val user = userModelMapper.mapFrom(from.user)
                id(user.id ?: "")
                username(user.username ?: "")
                name(user.fullName ?: "")
                email(user.email ?: "")
                isContact(user.isContact)
                isStaff(user.isStaff)
                token(from.token ?: "")
                refreshToken(from.refreshToken ?: "")
                accountTypeId(user.accountTypeId ?: -1)
                avatar(user.avatar ?: "")
                role(user.role)
                institutions(user.institutions)
                classes(user.classes)
                company(user.company ?: CompanyModel())
                mobile(user.mobile ?: "")
                phone(user.phone ?: "")
                children(user.children)
                language(user.language)
            }.build()

    override fun mapTo(to: User): LoginData {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
package com.parent.domain.user

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.common.lang.mapRemoteStringToBoolean
import com.parent.entities.*

/**
 * Created by mahmoud on 10/25/17.
 */
class UserRemoteModelMapper(private val roleRemoteModelMapper: ModelMapper<RoleRemote, Role>,
                            private val classModelMapper: ModelMapper<ClassRemote, ClassModel>,
                            private val institutionModelRemoteModelMapper: ModelMapper<InstitutionModelRemote, InstitutionModel>,
                            private val companyModelMapper: ModelMapper<CompanyRemote, CompanyModel>,
                            private val childRemoteModelMapper: ModelMapper<ChildModelData, ChildModel>,
                            private val languageModelMapper: ModelMapper<LanguageRemote, Language>) : ModelMapper<UserRemote, User> {
    override fun mapFrom(from: UserRemote): User =
            User(from.id ?: "",
                    from.username ?: "",
                    from.fullName ?: "",
                    from.email ?: "",
                    from.mobile ?: "",
                    from.phone ?: "",
                    from.isContact?.mapRemoteStringToBoolean() ?: false,
                    from.isStaff?.mapRemoteStringToBoolean() ?: false,
                    "",
                    "",
                    from.accountTypeId ?: -1,
                    from.avatar ?: "",
                    mapRole(from.role),
                    mapInstitutions(from.institutions),
                    from.company?.mapFromWith(companyModelMapper) ?: CompanyModel(),
                    from.children?.mapFromWith(childRemoteModelMapper) ?: listOf(),
                    mapClasses(from.classes),
                    mapLanguage(from.language)

            )

    override fun mapTo(to: User): UserRemote {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun mapLanguage(languageRemote: LanguageRemote?) =
            languageRemote?.mapFromWith(languageModelMapper) ?: Language()

    private fun mapRole(roleRemote: RoleRemote?) =
            roleRemote?.mapFromWith(roleRemoteModelMapper) ?: Role()

    private fun mapClasses(classes: List<ClassRemote>?): List<ClassModel> =
            classes?.mapFromWith(classModelMapper) ?: listOf()

    private fun mapInstitutions(institutions: List<InstitutionModelRemote>?): List<InstitutionModel> =
            institutions?.mapFromWith(institutionModelRemoteModelMapper) ?: listOf()
}
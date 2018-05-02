package com.parent.domain.user

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.base.mappers.mapToWith
import com.parent.domain.realm.base.toRealmList
import com.parent.domain.realm.entities.*
import com.parent.entities.*
import io.realm.RealmList

/**
 * Created by mahmoud on 10/5/17.
 */
class UserRealmModelMapper(private val roleModelMapper: ModelMapper<RoleRealm, Role>,
                           private val institutionModelRealmModelMapper: ModelMapper<InstitutionModelRealm,
                                   InstitutionModel>,
                           private val companyRealmModelMapper: ModelMapper<CompanyModelRealm, CompanyModel>,
                           private val childRealmModelMapper: ModelMapper<ChildModelRealm, ChildModel>) : ModelMapper<UserRealm, User> {
    override fun mapFrom(from: UserRealm): User =
            User(from.id,
                    from.username,
                    from.fullName,
                    from.email,
                    from.mobile,
                    from.phone,
                    from.isContact,
                    from.isStaff,
                    from.token,
                    from.refreshToken,
                    from.accountTypeId,
                    from.avatar,
                    from.role?.mapFromWith(roleModelMapper) ?: Role(),
                    mapInstitutionsFrom(from.institutions),
                    from.company?.mapFromWith(companyRealmModelMapper) ?: CompanyModel(),
                    from.children.mapFromWith(childRealmModelMapper))

    override fun mapTo(to: User): UserRealm =
            UserRealm(to.id ?: "",
                    to.username ?: "",
                    to.fullName ?: "",
                    to.email ?: "",
                    to.mobile,
                    to.phone,
                    to.isContact,
                    to.isStaff,
                    to.token ?: "",
                    to.refreshToken ?: "",
                    to.accountTypeId ?: -1,
                    to.avatar ?: "",
                    mapInstitutionsTo(to.institutions, to.id ?: ""),
                    to.company?.mapToWith(companyRealmModelMapper),
                    to.children.mapToWith(childRealmModelMapper).toRealmList(),
                    roleModelMapper.mapTo(to.role))

    private fun mapInstitutionsFrom(institutions: RealmList<InstitutionModelRealm>?): List<InstitutionModel> =
            institutions?.mapFromWith(institutionModelRealmModelMapper) ?: listOf()

    private fun mapInstitutionsTo(institutions: List<InstitutionModel>, userId: String): RealmList<InstitutionModelRealm> {

        var realmInst: RealmList<InstitutionModelRealm> = RealmList()

        for (item in institutions) {

            var institutionModelRealm: InstitutionModelRealm = institutionModelRealmModelMapper.mapTo(item)
            institutionModelRealm.userId = userId
            realmInst.add(institutionModelRealm)

        }
        return realmInst
    }

}
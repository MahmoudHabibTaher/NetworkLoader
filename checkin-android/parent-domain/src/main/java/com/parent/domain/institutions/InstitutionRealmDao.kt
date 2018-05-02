package com.parent.domain.institutions

import com.vicpin.krealmextensions.delete
import com.vicpin.krealmextensions.deleteAll
import com.vicpin.krealmextensions.query
import com.vicpin.krealmextensions.save
import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.InstitutionModel
import com.parent.domain.realm.entities.InstitutionModelRealm
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 10/24/17.
 */
class InstitutionRealmDao(private val institutionModelRealmModelMapper: ModelMapper<InstitutionModelRealm, InstitutionModel>
) : InstitutionDao {
    override fun saveInstitution(institution: InstitutionModel): Completable =
            Completable.fromAction {
                institutionModelRealmModelMapper.mapTo(institution).save()
            }

    override fun loadInstitutions(userId: String): Single<List<InstitutionModel>> =
            Single.fromCallable {
                InstitutionModelRealm().query({
                    it.equalTo("userId", userId)
                })
            }.flatMap { Single.just(mapToInstitutionModel(it)) }


    override fun deleteInstitution(id: String): Completable =
            Completable.fromAction { InstitutionModelRealm().delete { it.equalTo("id", id) } }

    private fun mapToInstitutionModel(institutions: List<InstitutionModelRealm>): List<InstitutionModel> {

        var list: MutableList<InstitutionModel> = arrayListOf()
        for (item in institutions) {
            list.add(institutionModelRealmModelMapper.mapFrom(item))
        }
        return list
    }

    override fun deleteAll(): Completable =
            Completable.fromAction {
                InstitutionModelRealm().deleteAll()
            }

}
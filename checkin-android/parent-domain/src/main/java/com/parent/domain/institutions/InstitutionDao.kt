package com.parent.domain.institutions

import com.parent.entities.InstitutionModel
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 10/24/17.
 */
interface InstitutionDao {

    fun saveInstitution(institution: InstitutionModel): Completable

    fun loadInstitutions(userId: String): Single<List<InstitutionModel>>

    fun deleteInstitution(id: String): Completable

    fun deleteAll(): Completable
}
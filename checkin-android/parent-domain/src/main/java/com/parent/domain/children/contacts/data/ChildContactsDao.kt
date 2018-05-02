package com.parent.domain.children.contacts.data

import com.parent.entities.ChildContactModel
import com.parent.entities.ChildContactRelationModel
import com.parent.entities.ChildContactRoleModel
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 12/8/17.
 */
interface ChildContactsDao {
    fun loadChildContacts(childId: String): Single<List<ChildContactModel>>

    fun loadChildContactsWithParentRole(childId: String): Single<List<ChildContactModel>>

    fun saveChildContacts(childId: String, childContacts: List<ChildContactModel>): Completable

    fun saveChildPickUpContacts(childId: String, childContacts: List<ChildContactModel>): Completable

    fun deleteChildContacts(childId: String): Completable

    fun loadChildPickUpContacts(childId: String): Single<List<ChildContactModel>>

    fun loadChildContactsRoles(): Single<List<ChildContactRoleModel>>

    fun loadChildContactsRelations(): Single<List<ChildContactRelationModel>>

    fun saveChildContactRoles(roles: List<ChildContactRoleModel>): Completable

    fun saveChildContactRelation(relations: List<ChildContactRelationModel>): Completable

}
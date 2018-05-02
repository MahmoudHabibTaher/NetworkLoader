package com.parent.domain.children.contacts.data

import com.parent.domain.base.BaseDataSource
import com.parent.entities.ChildContactModel
import com.parent.entities.ChildContactRelationModel
import com.parent.entities.ChildContactRoleModel
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 12/6/17.
 */
interface ChildContactsDataSource : BaseDataSource {

    fun loadChildContacts(childId: String, page: Int): Single<List<ChildContactModel>>

    fun loadChildContactsWithParentRole(childId: String, page: Int): Single<List<ChildContactModel>>

    fun loadChildPickUpContacts(childId: String): Single<List<ChildContactModel>>

    fun loadChildContactProfileDetails(childId: String, contactId: String): Single<ChildContactModel>

    fun loadChildContactsRoles(): Single<List<ChildContactRoleModel>>

    fun loadChildContactsRelations(): Single<List<ChildContactRelationModel>>

    fun editChildContactProfile(childId: String, contactId: String, contactModel: ChildContactModel): Single<String>

    fun addNewChildContactProfile(childId: String, contactModel: ChildContactModel): Single<String>

    fun saveChildContacts(childId: String, children: List<ChildContactModel>): Completable

    fun saveChildPickUpContacts(childId: String, childContacts: List<ChildContactModel>): Completable

    fun saveChildContactRoles(roles: List<ChildContactRoleModel>): Completable

    fun saveChildContactRelation(relations: List<ChildContactRelationModel>): Completable

    fun deleteChildContacts(classId: String): Completable

    fun removeContactFromChild(childId: String, contactId: String): Single<String>

}
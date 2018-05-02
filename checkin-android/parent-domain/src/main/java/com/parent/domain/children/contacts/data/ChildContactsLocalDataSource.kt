package com.parent.domain.children.contacts.data

import com.parent.entities.ChildContactModel
import com.parent.entities.ChildContactRelationModel
import com.parent.entities.ChildContactRoleModel
import com.parent.entities.exceptions.EmptyListException
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 12/8/17.
 */
class ChildContactsLocalDataSource(private val childContactsDao: ChildContactsDao) : ChildContactsDataSource {
    override fun removeContactFromChild(childId: String, contactId: String): Single<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addNewChildContactProfile(childId: String, contactModel: ChildContactModel): Single<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun editChildContactProfile(childId: String, contactId: String, contactModel: ChildContactModel): Single<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadChildContactProfileDetails(childId: String, contactId: String): Single<ChildContactModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveChildPickUpContacts(childId: String, childContacts: List<ChildContactModel>): Completable =
            childContactsDao.saveChildPickUpContacts(childId, childContacts)

    override fun saveChildContacts(childId: String, childContacts: List<ChildContactModel>): Completable =
            childContactsDao.saveChildContacts(childId, childContacts)

    override fun deleteChildContacts(childId: String): Completable =
            childContactsDao.deleteChildContacts(childId)

    override fun loadChildPickUpContacts(childId: String): Single<List<ChildContactModel>> =
            childContactsDao.loadChildPickUpContacts(childId).flatMap {
                if (it.isEmpty()) Single.error(EmptyListException(""))
                else Single.just(it)
            }

    override fun loadChildContacts(childId: String, page: Int): Single<List<ChildContactModel>> =
            childContactsDao.loadChildContacts(childId).flatMap {
                if (it.isEmpty()) Single.error(EmptyListException(""))
                else Single.just(it)
            }

    override fun loadChildContactsWithParentRole(childId: String, page: Int): Single<List<ChildContactModel>> =
            childContactsDao.loadChildContactsWithParentRole(childId).flatMap {
                if (it.isEmpty()) Single.error(EmptyListException(""))
                else Single.just(it)
            }

    override fun loadChildContactsRoles(): Single<List<ChildContactRoleModel>> =
            childContactsDao.loadChildContactsRoles().flatMap {
                if (it.isEmpty()) Single.error(EmptyListException(""))
                else Single.just(it)
            }

    override fun loadChildContactsRelations(): Single<List<ChildContactRelationModel>> =
            childContactsDao.loadChildContactsRelations().flatMap {
                if (it.isEmpty()) Single.error(EmptyListException(""))
                else Single.just(it)
            }

    override fun saveChildContactRoles(roles: List<ChildContactRoleModel>): Completable =
            childContactsDao.saveChildContactRoles(roles)

    override fun saveChildContactRelation(relations: List<ChildContactRelationModel>): Completable =
            childContactsDao.saveChildContactRelation(relations)


    override fun invalidateCache() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
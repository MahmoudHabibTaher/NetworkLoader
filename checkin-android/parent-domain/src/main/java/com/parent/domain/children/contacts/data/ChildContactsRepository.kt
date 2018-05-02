package com.parent.domain.children.contacts.data

import com.parent.domain.base.BaseRepository
import com.parent.entities.ChildContactModel
import com.parent.entities.ChildContactRelationModel
import com.parent.entities.ChildContactRoleModel
import com.parent.entities.exceptions.EmptyListException
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 12/6/17.
 */
class ChildContactsRepository(private val remoteDataSource: ChildContactsDataSource,
                              private val localDataSource: ChildContactsDataSource) : BaseRepository(), ChildContactsDataSource {
    override fun loadChildContacts(childId: String, page: Int): Single<List<ChildContactModel>> =
            when {
                isCacheInvalid() -> loadRemoteChildContacts(childId, page)
                else -> {
                    loadLocalChildContacts(childId, page).onErrorResumeNext {
                        if (it is EmptyListException) {
                            loadRemoteChildContacts(childId, page)
                        } else {
                            Single.error(it)
                        }
                    }
                }
            }

    override fun loadChildContactsWithParentRole(childId: String, page: Int): Single<List<ChildContactModel>> =
            remoteDataSource.loadChildContactsWithParentRole(childId, page).doOnSuccess {
                saveChildContacts(childId, it).doOnComplete { setCacheValid(true) }.blockingAwait()
            }

    override fun loadChildPickUpContacts(childId: String): Single<List<ChildContactModel>> =
            when {
                isCacheInvalid() -> loadRemoteChildPickupContacts(childId)
                else -> {
                    loadLocalChildPickupContacts(childId).onErrorResumeNext {
                        if (it is EmptyListException) {
                            loadRemoteChildPickupContacts(childId)
                        } else {
                            Single.error(it)
                        }
                    }
                }
            }

    override fun loadChildContactProfileDetails(childId: String, contactId: String): Single<ChildContactModel> =
            when {
                isCacheInvalid() -> loadRemoteChildContactProfile(childId, contactId)
                else -> {
                    loadLocalChildContactProfile(childId, contactId).onErrorResumeNext {
                        if (it is EmptyListException) {
                            loadRemoteChildContactProfile(childId, contactId)
                        } else {
                            Single.error(it)
                        }
                    }
                }
            }

    override fun loadChildContactsRoles(): Single<List<ChildContactRoleModel>> =
            remoteDataSource.loadChildContactsRoles().doOnSuccess {
                saveChildContactRoles(it)
            }

    override fun loadChildContactsRelations(): Single<List<ChildContactRelationModel>> =
            remoteDataSource.loadChildContactsRelations().doOnSuccess {
                saveChildContactRelation(it)
            }

    override fun editChildContactProfile(childId: String, contactId: String, contactModel: ChildContactModel): Single<String> =
            remoteDataSource.editChildContactProfile(childId, contactId, contactModel)

    override fun addNewChildContactProfile(childId: String, contactModel: ChildContactModel): Single<String> =
            remoteDataSource.addNewChildContactProfile(childId, contactModel)

    override fun saveChildContacts(childId: String, childContacts: List<ChildContactModel>): Completable =
            deleteChildContacts(childId)
                    .andThen(localDataSource.saveChildContacts(childId, childContacts))

    override fun saveChildPickUpContacts(childId: String, childContacts: List<ChildContactModel>): Completable =
            deleteChildContacts(childId)
                    .andThen(localDataSource.saveChildPickUpContacts(childId, childContacts))

    override fun saveChildContactRoles(roles: List<ChildContactRoleModel>): Completable =
            localDataSource.saveChildContactRoles(roles)

    override fun saveChildContactRelation(relations: List<ChildContactRelationModel>): Completable =
            localDataSource.saveChildContactRelation(relations)


    override fun deleteChildContacts(classId: String): Completable =
            localDataSource.deleteChildContacts(classId)

    override fun invalidateCache() =
            setCacheValid(false)

    fun loadRemoteChildContacts(childId: String, page: Int): Single<List<ChildContactModel>> =
            remoteDataSource.loadChildContacts(childId, page).doOnSuccess {
                saveChildContacts(childId, it).doOnComplete { setCacheValid(true) }.blockingAwait()
            }

    fun loadRemoteChildPickupContacts(childId: String): Single<List<ChildContactModel>> =
            remoteDataSource.loadChildPickUpContacts(childId).doOnSuccess {
                saveChildPickUpContacts(childId, it).doOnComplete { setCacheValid(true) }.blockingAwait()
            }

    fun loadRemoteChildContactProfile(childId: String, contactId: String): Single<ChildContactModel> =
            remoteDataSource.loadChildContactProfileDetails(childId, contactId)

    fun loadLocalChildContacts(childId: String, page: Int): Single<List<ChildContactModel>> =
            localDataSource.loadChildContacts(childId, page)

    fun loadLocalChildPickupContacts(childId: String): Single<List<ChildContactModel>> =
            localDataSource.loadChildPickUpContacts(childId)

    fun loadLocalChildContactProfile(childId: String, contactId: String): Single<ChildContactModel> =
            localDataSource.loadChildContactProfileDetails(childId, contactId)

    override fun removeContactFromChild(childId: String, contactId: String): Single<String> =
            remoteDataSource.removeContactFromChild(childId, contactId)

}
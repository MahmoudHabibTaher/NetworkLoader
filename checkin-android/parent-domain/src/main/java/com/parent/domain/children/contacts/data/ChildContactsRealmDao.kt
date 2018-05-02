package com.parent.domain.children.contacts.data

import com.vicpin.krealmextensions.delete
import com.vicpin.krealmextensions.query
import com.vicpin.krealmextensions.queryAll
import com.vicpin.krealmextensions.saveAll
import com.parent.domain.realm.entities.ChildContactModelRealm
import com.parent.domain.realm.entities.ChildContactRelationModelRealm
import com.parent.domain.realm.entities.ChildContactRoleModelRealm
import com.parent.entities.ChildContactModel
import com.parent.entities.ChildContactRelationModel
import com.parent.entities.ChildContactRoleModel
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.base.mappers.mapToWith
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 12/8/17.
 */
class ChildContactsRealmDao(private val childContactRealmModelMapper: ModelMapper<ChildContactModelRealm, ChildContactModel>,
                            private val childContactRelationRealmModelMapper: ModelMapper<ChildContactRelationModelRealm, ChildContactRelationModel>,
                            private val childContactRoleRealmModelMapper: ModelMapper<ChildContactRoleModelRealm, ChildContactRoleModel>) : ChildContactsDao {

    override fun loadChildContacts(childId: String): Single<List<ChildContactModel>> =
            Single.fromCallable {
                ChildContactModelRealm().query { it.equalTo("childId", childId) } mapFromWith childContactRealmModelMapper
            }

    override fun loadChildContactsWithParentRole(childId: String): Single<List<ChildContactModel>> =
            Single.fromCallable {
                ChildContactModelRealm().query {
                    it.equalTo("childId", childId).equalTo("roleId", ChildContactRoleModel.PARENT_MODEL_ROLE)
                } mapFromWith childContactRealmModelMapper
            }

    override fun loadChildPickUpContacts(childId: String): Single<List<ChildContactModel>> =
            Single.fromCallable {
                ChildContactModelRealm().query {
                    it.equalTo("childId", childId).equalTo("hasPickup", true)
                } mapFromWith childContactRealmModelMapper
            }

    override fun saveChildContacts(childId: String, childContacts: List<ChildContactModel>): Completable =
            Completable.fromAction {
                val list = childContacts mapToWith childContactRealmModelMapper
                list.forEach {
                    it.childId = childId
                }
                list.saveAll()
            }

    override fun saveChildPickUpContacts(childId: String, childContacts: List<ChildContactModel>): Completable =
            Completable.fromAction {
                val list = childContacts mapToWith childContactRealmModelMapper
                list.forEach {
                    it.childId = childId
                    it.hasPickup = true
                }
                list.saveAll()
            }


    override fun deleteChildContacts(childId: String): Completable =
            Completable.fromAction {
                ChildContactModelRealm().delete { it.equalTo("childId", childId) }
            }

    override fun loadChildContactsRoles(): Single<List<ChildContactRoleModel>> =
            Single.fromCallable {
                ChildContactRoleModelRealm().queryAll() mapFromWith childContactRoleRealmModelMapper
            }

    override fun loadChildContactsRelations(): Single<List<ChildContactRelationModel>> =
            Single.fromCallable {
                ChildContactRelationModelRealm().queryAll() mapFromWith childContactRelationRealmModelMapper
            }

    override fun saveChildContactRoles(roles: List<ChildContactRoleModel>): Completable =
            Completable.fromAction {
                val list = roles mapToWith childContactRoleRealmModelMapper
                list.saveAll()
            }

    override fun saveChildContactRelation(relations: List<ChildContactRelationModel>): Completable =
            Completable.fromAction {
                val list = relations mapToWith childContactRelationRealmModelMapper
                list.saveAll()
            }

}
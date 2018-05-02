package com.parent.domain.children.contacts.data

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.base.mappers.mapToWith
import com.parent.domain.common.data.source.remote.BaseRemoteDataSource
import com.parent.domain.common.network.ErrorConstants
import com.parent.domain.common.network.toErrorResponseModel
import com.parent.entities.*
import com.parent.entities.exceptions.ValidationErrorsException
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.HttpException

/**
 * Created by mahmoud on 12/8/17.
 */
class ChildContactsRemoteDataSource(private val api: ChildContactsApi,
                                    private val childContactResponseRemoteModelMapper: ModelMapper<ChildContactPageRemote, List<ChildContactModel>>,
                                    private val childContactRemoteModelMapper: ModelMapper<ChildContactModelResponse, ChildContactModel>,
                                    private val childContactRequestRemoteModelMapper: ModelMapper<ChildContactModelRequest, ChildContactModel>,
                                    private val addNewChildContactRequestRemoteModelMapper: ModelMapper<AddNewChildContactRequest, ChildContactModel>,
                                    private val childContactRealationRemoteModelMapper: ModelMapper<ChildContactRelationModelRemote, ChildContactRelationModel>,
                                    private val childContactRoleRemoteModelMapper: ModelMapper<ChildContactRoleModelRemote, ChildContactRoleModel>
) : ChildContactsDataSource, BaseRemoteDataSource() {

    override fun saveChildContactRoles(roles: List<ChildContactRoleModel>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveChildContactRelation(relations: List<ChildContactRelationModel>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveChildPickUpContacts(childId: String, childContacts: List<ChildContactModel>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveChildContacts(childId: String, children: List<ChildContactModel>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteChildContacts(classId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadChildContacts(childId: String, page: Int): Single<List<ChildContactModel>> =
            callSingle(api.getChildContacts(childId, page).flatMap {
                val data = it.data ?: ChildContactPageRemote()
                Single.just(data.mapFromWith(childContactResponseRemoteModelMapper))
            })

    override fun loadChildContactsWithParentRole(childId: String, page: Int): Single<List<ChildContactModel>> =
            callSingle(api.getChildContactsWithParentRole(childId, ChildContactRoleModel.PARENT_MODEL_ROLE).flatMap {
                val data = it.data ?: listOf()
                Single.just(data.mapFromWith(childContactRemoteModelMapper))
            })

    override fun loadChildContactsRoles(): Single<List<ChildContactRoleModel>> =
            callSingle(api.getChildContactsRoles().flatMap {
                val data = it.data ?: emptyList()
                Single.just(data mapFromWith childContactRoleRemoteModelMapper)
            })

    override fun loadChildContactsRelations(): Single<List<ChildContactRelationModel>> =
            callSingle(api.getChildContactsRelations().flatMap {
                val data = it.data ?: emptyList()
                Single.just(data mapFromWith childContactRealationRemoteModelMapper)
            })

    override fun loadChildPickUpContacts(childId: String): Single<List<ChildContactModel>> =
            callSingle(api.getChildPickUpContacts(childId).flatMap {
                val data = it.data ?: emptyList()
                Single.just(data mapFromWith childContactRemoteModelMapper)
            })

    override fun loadChildContactProfileDetails(childId: String, contactId: String): Single<ChildContactModel> =
            callSingle(api.getChildContactProfileDetails(childId, contactId).flatMap {
                val data = it.data ?: ChildContactModelResponse()
                Single.just(data mapFromWith childContactRemoteModelMapper)
            })

    override fun editChildContactProfile(childId: String, contactId: String, contactModel: ChildContactModel): Single<String> =
            callSingle(api.editChildContactProfile(childId, contactId, contactModel.mapToWith(childContactRequestRemoteModelMapper)).flatMap {
                Single.fromCallable {
                    it.message ?: ""
                }
            })

    override fun addNewChildContactProfile(childId: String, contactModel: ChildContactModel): Single<String> =
            callSingle(api.addNewChildContactProfile(childId, contactModel.mapToWith(addNewChildContactRequestRemoteModelMapper)).flatMap {
                Single.just(it.message ?: "")
            }, onError = {
                it is HttpException && it.code() == ErrorConstants.INPUT_VALIDATION_400
            }, handleError = {
                if (it is HttpException) {
                    val code = it.code()
                    when (code) {
                        ErrorConstants.INPUT_VALIDATION_400 -> {
                            val error: ValidationErrorsResponse? = it.toErrorResponseModel()
                            var exc = ValidationErrorsException(error?.message ?: "", error?.data
                                    ?: emptyList())
                            Single.error(exc)
                        }
                        else -> Single.error(it)
                    }
                } else {
                    Single.error(it)
                }
            })

    override fun removeContactFromChild(childId: String, contactId: String): Single<String> =
            callSingle(api.removeContactFromChild(childId, contactId).flatMap {
                Single.just(it.message ?: "")
            })

    override fun invalidateCache() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
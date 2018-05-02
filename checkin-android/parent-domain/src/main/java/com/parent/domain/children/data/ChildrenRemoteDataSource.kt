package com.parent.domain.children.data

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.base.mappers.mapToWith
import com.parent.domain.common.data.source.remote.BaseRemoteDataSource
import com.parent.domain.common.network.ErrorConstants
import com.parent.domain.common.network.toErrorResponseModel
import com.parent.domain.datetime.IDateTimeConverter
import com.parent.entities.*
import com.parent.entities.exceptions.ItemNotFoundException
import com.parent.entities.exceptions.NoChildPermissionsAddedException
import com.parent.entities.exceptions.NotAuthorizedException
import com.parent.entities.exceptions.ValidationErrorsException
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.HttpException

/**
 * Created by mahmoud on 12/8/17.
 */
class ChildrenRemoteDataSource(private val api: ChildrenApi,
                               private val classChildRemoteModelMapper: ModelMapper<ClassChildRemote, ClassChild>,
                               private val childRemoteModelMapper: ModelMapper<ChildModelData, ChildModel>,
                               private val childRequestModelMapper: ModelMapper<ChildModelRequest, ChildModel>,
                               private val childLeavesModelMapper: ModelMapper<ChildLeavesRemote, ChildLeaves>,
                               private val childPickUpModelMapper: ModelMapper<ChildPickupModelRemote, ChildPickupModel>,
                               private val childPickUpRequestModelMapper: ModelMapper<ChildPickupRequest, ChildPickupModel>,
                               private val childHealthRemoteModelMapper: ModelMapper<ChildHealthModelData, ChildHealthModel>,
                               private val childLeaveRequestModelMapper: ModelMapper<ChildLeaveRequestModelRemote, ChildLeaveRequestModel>,
                               private val reportItemRemoteModelMapper: ModelMapper<ReportItemRemote, ReportItem>,
                               private val dateTimeConverter: IDateTimeConverter,
                               private val childPermissionRemoteModelMapper: ModelMapper<ChildPermissionRemote, ChildPermission>,
                               private val childGalleryRemoteModelMapper: ModelMapper<ChildGalleryResponse, ChildGalleryModel>) :
        ChildrenDataSource, BaseRemoteDataSource() {

    override fun loadClassChildren(classId: String): Single<List<ClassChild>> =
            callSingle(api.getClassChildren(classId).flatMap {
                val data = it.data?.children ?: emptyList()
                Single.just(data mapFromWith classChildRemoteModelMapper)
            }.onErrorResumeNext {
                if (it is HttpException) {
                    val code = it.code()
                    val message = it.toErrorResponseModel<BaseErrorResponse<Any>>()?.message
                            ?: ""
                    when (code) {
                        ErrorConstants.NOT_AUTHORIZED_403 -> Single.error(NotAuthorizedException(message))
                        ErrorConstants.NOT_FOUND_404 -> Single.error(ItemNotFoundException(message))
                        else -> Single.error(it)
                    }
                } else {
                    Single.error(it)
                }
            })


    override fun loadChildGallery(childId: String, type: String, pageNumber: Int): Single<ChildGalleryModel> =
            callSingle(api.getChildGallery(childId, type, pageNumber).flatMap {
                val data = it.data ?: ChildGalleryResponse()
                Single.just(data mapFromWith childGalleryRemoteModelMapper)
            })


    override fun loadChildPickUp(childId: String): Single<ChildPickupModel> =
            callSingle(api.getChildPickup(childId).flatMap {
                val data = it.data?.pickup ?: ChildPickupModelRemote()
                val pickupModel = data mapFromWith childPickUpModelMapper
                pickupModel.pickupText = it.message ?: ""
                Single.just(pickupModel)
            })

    override fun addChildPickUpInfo(childId: String, childPickUpModel: ChildPickupModel): Completable =
            callCompletable(api.editChildPickup(childId, childPickUpModel.mapToWith(childPickUpRequestModelMapper)), onError = {
                it is HttpException && it.code() == ErrorConstants.INPUT_VALIDATION_400
            }, handleError = {
                if (it is HttpException) {
                    val code = it.code()
                    when (code) {
                        ErrorConstants.INPUT_VALIDATION_400 -> {
                            try {
                                val error: ValidationErrorsResponse? = it.toErrorResponseModel()
                                var exc = ValidationErrorsException(error?.message
                                        ?: "", error?.data ?: emptyList())
                                Completable.error(exc)
                            } catch (e: Exception) {
                                e.printStackTrace()

                                Completable.error(it)
                            }
                        }
                        else -> Completable.error(it)
                    }
                } else {
                    Completable.error(it)
                }
            })

    override fun reportChildVacation(childLeaveRequestModel: ChildLeaveRequestModel): Completable =
            callCompletable(api.reportChildVacation(childLeaveRequestModel.mapToWith(childLeaveRequestModelMapper)))

    override fun reportChildSick(childLeaveRequestModel: ChildLeaveRequestModel): Completable =
            callCompletable(api.reportChildSick(childLeaveRequestModel.mapToWith(childLeaveRequestModelMapper)))

    override fun editChildDetails(childId: String, child: ChildModel): Completable =
            callCompletable(api.editChildDetails(childId, child.mapToWith(childRequestModelMapper)), onError = {
                it is HttpException && it.code() == ErrorConstants.INPUT_VALIDATION_400
            }, handleError = {
                if (it is HttpException) {
                    val code = it.code()
                    when (code) {
                        ErrorConstants.INPUT_VALIDATION_400 -> {
                            try {
                                val error: ValidationErrorsResponse? = it.toErrorResponseModel()
                                var exc = ValidationErrorsException(error?.message
                                        ?: "", error?.data ?: emptyList())
                                Completable.error(exc)
                            } catch (e: Exception) {
                                e.printStackTrace()

                                Completable.error(it)
                            }
                        }
                        else -> Completable.error(it)
                    }
                } else {
                    Completable.error(it)
                }
            })

    override fun getChildHealthDetails(childId: String, institutionId: String): Single<ChildHealthModel> =
            callSingle(api.getChildHealthDetails(childId, institutionId).flatMap {
                val data = it.data ?: ChildHealthModelData()
                Single.just(childHealthRemoteModelMapper.mapFrom(data))
            })

    override fun editChildHealthDetails(childId: String, institutionId: String, childHealth: ChildHealthModel): Completable =
            callCompletable(api.editChildHealthDetails(childId, childHealthRemoteModelMapper.mapTo(childHealth)))

    override fun loadChildDetails(childId: String, institutionId: String): Single<ChildModel> =
            callSingle(api.getChildDetails(childId, institutionId).flatMap {
                val data = it.data ?: ChildModelData()
                Single.just(childRemoteModelMapper.mapFrom(data))
            })

    override fun loadChildPermissions(childId: String, institutionId: String): Single<List<ChildPermission>> =
            callSingle(api.getChildPermissions(childId, institutionId).flatMap {
                Single.just(it.data?.mapFromWith(childPermissionRemoteModelMapper) ?: emptyList())
            }, onError = {
                it is HttpException && it.code() == ErrorConstants.NOT_FOUND_404
            }, handleError = {
                val exception = it as HttpException
                val error = exception.toErrorResponseModel<BaseErrorResponse<Any>>()
                Single.error(NoChildPermissionsAddedException(error?.message
                        ?: ""))
            })

    override fun addChildPermission(childId: String, instituteId: String, permissionName: String,
                                    permissionDescription: String): Single<AddChildPermissionResult> =
            callSingle(AddChildPermissionRemoteRequest.Builder().instituteId(instituteId)
                    .title(permissionName)
                    .description(permissionDescription)
                    .build().let {
                        api.addChildPermission(childId, it).flatMap {
                            val message = it.message
                            Single.just(AddChildPermissionResult.Builder()
                                    .message(message ?: "").build())
                        }
                    })

    override fun saveClassChildren(classId: String, children: List<ClassChild>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteClassChildren(classId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadChildLeaves(childId: String): Single<ChildLeaves> =
            callSingle(api.getChildLeaves(childId).flatMap {
                Single.just(it.mapFromWith(childLeavesModelMapper))
            })

    override fun saveChildLeaves(childId: String, childLeaves: ChildLeaves): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteChildLeaves(childId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadChildDailyReport(childId: String, date: Long): Single<DailyReport> =
            callSingle(api.getChildDailyReport(childId,
                    dateTimeConverter.convertToServerDate(date) ?: "").flatMap {
                val reportItems = it.data?.mapFromWith(reportItemRemoteModelMapper) ?: emptyList()
                Single.just(DailyReport.Builder().childId(childId).date(date).items(reportItems).build())
            })

    override fun saveChildDailyReport(dailyReport: DailyReport): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteChildDailyReport(childId: String, date: Long): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveChildPermissions(childId: String, permissions: List<ChildPermission>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteChildPermissions(childId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveChildPermissionReply(childId: String, contactId: String,
                                          permissionId: String, reply: PermissionReply.Status): Completable =
            callCompletable(Single.fromCallable {
                val replyText = when (reply) {
                    PermissionReply.Status.YES -> PermissionReplyRemote.Status.YES
                    PermissionReply.Status.NO -> PermissionReplyRemote.Status.NO
                    else -> PermissionReplyRemote.Status.NO_REPLY
                }

                PermissionReplyRequest(contactId, replyText)
            }.flatMapCompletable { request ->
                api.saveChildPermissionReply(childId, permissionId, request)
            })

    override fun checkInChild(childId: String): Completable =
            callCompletable(api.checkInChild(childId))

    override fun checkOutChild(childId: String): Completable =
            callCompletable(api.checkOutChild(childId))

    override fun invalidateCache() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
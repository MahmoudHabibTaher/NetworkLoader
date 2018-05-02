package com.parent.domain.institutions

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.common.data.source.remote.BaseRemoteDataSource
import com.parent.domain.common.lang.toInt
import com.parent.domain.common.network.ErrorConstants
import com.parent.domain.common.network.toErrorResponseModel
import com.parent.entities.*
import com.parent.entities.exceptions.ParseResponseException
import com.parent.entities.exceptions.ValidationErrorsException
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.HttpException

/**
 * Created by mahmoud on 11/29/17.
 */
class InstituteOptionsRemoteDataSource(private val api: InstituteOptionsApi,
                                       private val modelMapper: ModelMapper<InstituteOptionsRemote, InstituteOptions>) : InstituteOptionsDataSource, BaseRemoteDataSource() {
    override fun loadInstituteOptions(instituteId: String): Single<InstituteOptions> =
            callSingle(api.getInstituteOptions(instituteId).flatMap {
                val data = it.data
                if (data != null) {
                    Single.just(data.mapFromWith(modelMapper))
                } else {
                    Single.error(ParseResponseException())
                }
            })

    override fun updateInstituteOptions(options: InstituteOptions): Completable =
            callCompletable(prepareUpdateRequest(options).flatMapCompletable {
                api.updateInstituteOptions(options.id, it)
            }, onError = {
                it is HttpException && it.code() == ErrorConstants.INPUT_VALIDATION_400
            }, handleError = {
                if (it is HttpException) {
                    val code = it.code()
                    when (code) {
                        ErrorConstants.INPUT_VALIDATION_400 -> {
                            val errorResponse = it.toErrorResponseModel<ValidationErrorsResponse>()
                            Completable.error(ValidationErrorsException(errorResponse?.message
                                    ?: "", errorResponse?.data ?: emptyList()))
                        }
                        else -> Completable.error(it)
                    }
                } else {
                    Completable.error(it)
                }
            })

    override fun saveInstituteOptions(options: InstituteOptions): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteInstituteOptions(instituteId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadTimeFormats(): Single<List<TimeFormat>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadDateFormats(): Single<List<DateFormat>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadWeekStarts(): Single<List<WeekStart>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun invalidateCache() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun prepareUpdateRequest(options: InstituteOptions): Single<UpdateInstituteOptionsRequest> =
            Single.fromCallable {
                UpdateInstituteOptionsRequest.Builder()
                        .name(options.name)
                        .street(options.street)
                        .countryId(options.country.id)
                        .cityName(options.city.name)
                        .adminName(options.contactPerson)
                        .image(options.avatar)
                        .website(options.website)
                        .email(options.email)
                        .phone(options.phone)
                        .latitude(options.latitude)
                        .longitude(options.longitude)
                        .timezone(options.timezone.key)
                        .zipCode(options.zipCode)
                        .weekStart(options.weekStart)
                        .companyId(options.companyId)
                        .checkInDiameter(options.checkInDiameter)
                        .dateFormat(options.dateFormat)
                        .timeFormat(options.timeFormat)
                        .showWeeksNumber(options.showWeeksNumber.toInt())
                        .build()
            }
}

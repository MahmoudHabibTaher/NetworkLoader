package com.parent.domain.status

import com.parent.domain.auth.ISessionManager
import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.common.date.now
import com.parent.domain.datetime.DateFormatter
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import com.parent.entities.OpeningHourDayModel
import com.parent.entities.OpeningHourDayModelView
import io.reactivex.Single
import java.util.*

/**
 * Created by mahmoud on 9/19/17.
 */
class CallGetTodayOpeningHours(threadExecutor: ThreadExecutor,
                               postThreadExecutor: PostThreadExecutor,
                               private val sessionManager: ISessionManager,
                               private val statusesDataSource: StatusesDataSource,
                               private val dateFormatter: DateFormatter) :
        BaseSingleUseCase<OpeningHourDayModelView>(threadExecutor, postThreadExecutor) {

    override fun buildUseCaseSingle(params: Params): Single<OpeningHourDayModelView> {

        var currentServerTime: String = params.get(ReportChildrenParams.PARAM_CURRENT_TIME,
                dateFormatter.formatToServerMainTime(now()))

        var currentServerTimeInMillis: Long = dateFormatter.getTimeInMillisFromDate(currentServerTime)
        var todaysDayNumber: Int = dateFormatter.getWeekDay(currentServerTime)

        return sessionManager.getCurrentInstitute().flatMap {
            statusesDataSource.getInstitutionOpeningHours(it.id).flatMap {
                var today = mapToViewFromModel(it[0], currentServerTimeInMillis)
                var todayIndex = 0
                for (day in it) {

                    when (day.dayName) {
                        OpeningHourDayModel.MONDAY -> {
                            if (todaysDayNumber == Calendar.MONDAY) {
                                today = mapToViewFromModel(day, currentServerTimeInMillis)
                            }
                        }
                        OpeningHourDayModel.TUESDAY -> {
                            if (todaysDayNumber == Calendar.TUESDAY) {
                                today = mapToViewFromModel(day, currentServerTimeInMillis)
                            }
                        }
                        OpeningHourDayModel.WEDNESDAY -> {
                            if (todaysDayNumber == Calendar.WEDNESDAY) {
                                today = mapToViewFromModel(day, currentServerTimeInMillis)
                            }
                        }
                        OpeningHourDayModel.THURSDAY -> {
                            if (todaysDayNumber == Calendar.THURSDAY) {
                                today = mapToViewFromModel(day, currentServerTimeInMillis)
                            }
                        }
                        OpeningHourDayModel.FRIDAY -> {
                            if (todaysDayNumber == Calendar.FRIDAY) {
                                today = mapToViewFromModel(day, currentServerTimeInMillis)
                            }
                        }
                        OpeningHourDayModel.SATURDAY -> {
                            if (todaysDayNumber == Calendar.SATURDAY) {
                                today = mapToViewFromModel(day, currentServerTimeInMillis)
                            }
                        }
                        OpeningHourDayModel.SUNDAY -> {
                            if (todaysDayNumber == Calendar.SUNDAY) {
                                today = mapToViewFromModel(day, currentServerTimeInMillis)
                            }
                        }
                    }
                }

                Single.just(today)
            }
        }
    }

    fun mapToViewFromModel(day: OpeningHourDayModel, nowTime: Long): OpeningHourDayModelView {

        var dayView = OpeningHourDayModelView(day.id,
                dateFormatter.formatDisplayTimeToTimeStamp(nowTime, day.openingHour),
                dateFormatter.formatDisplayTimeToTimeStamp(nowTime, day.closingHour), day.isWeekend, day.dayNumber, day.dayName)
        return dayView
    }
}

package com.parent.domain.institutions

import com.parent.entities.DateFormat
import com.parent.entities.InstituteOptions
import com.parent.entities.TimeFormat
import com.parent.entities.WeekStart
import com.parent.domain.base.BaseDataSource
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 11/28/17.
 */
interface InstituteOptionsDataSource : BaseDataSource {
    fun loadInstituteOptions(instituteId: String): Single<InstituteOptions>

    fun updateInstituteOptions(options: InstituteOptions): Completable

    fun saveInstituteOptions(options: InstituteOptions): Completable

    fun deleteInstituteOptions(instituteId: String): Completable

    fun loadTimeFormats(): Single<List<TimeFormat>>

    fun loadDateFormats(): Single<List<DateFormat>>

    fun loadWeekStarts(): Single<List<WeekStart>>
}
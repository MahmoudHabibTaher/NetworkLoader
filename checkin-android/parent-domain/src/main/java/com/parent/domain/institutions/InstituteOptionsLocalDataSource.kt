package com.parent.domain.institutions

import android.content.res.Resources
import com.parent.entities.DateFormat
import com.parent.entities.InstituteOptions
import com.parent.entities.TimeFormat
import com.parent.domain.R
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 12/2/17.
 */
class InstituteOptionsLocalDataSource(private val resources: Resources) : InstituteOptionsDataSource {
    override fun loadInstituteOptions(instituteId: String): Single<InstituteOptions> =
            Single.error(NoSuchElementException())

    override fun updateInstituteOptions(options: InstituteOptions): Completable =
            Completable.complete()

    override fun saveInstituteOptions(options: InstituteOptions): Completable =
            Completable.complete()

    override fun deleteInstituteOptions(instituteId: String): Completable =
            Completable.complete()

    override fun loadTimeFormats(): Single<List<TimeFormat>> =
            Single.fromCallable {
                val names = resources.getStringArray(R.array.time_format_names)
                val formats = resources.getStringArray(R.array.time_format_formats)

                names.mapIndexed { index, name ->
                    TimeFormat(name, formats[index])
                }
            }

    override fun loadDateFormats(): Single<List<DateFormat>> =
            Single.fromCallable {
                val names = resources.getStringArray(R.array.date_format_names)
                val formats = resources.getStringArray(R.array.date_format_formats)

                names.mapIndexed { index, name ->
                    DateFormat(name, formats[index])
                }
            }

    override fun loadWeekStarts(): Single<List<WeekStart>> =
            Single.fromCallable {
                val names = resources.getStringArray(R.array.week_start_names)
                val days = resources.getIntArray(R.array.week_start_days)

                names.mapIndexed { index, name ->
                    WeekStart(name, days[index])
                }
            }

    override fun invalidateCache() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

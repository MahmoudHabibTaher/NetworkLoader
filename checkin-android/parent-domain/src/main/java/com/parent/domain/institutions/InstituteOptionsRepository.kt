package com.parent.domain.institutions

import com.parent.domain.base.BaseRepository
import com.parent.entities.DateFormat
import com.parent.entities.InstituteOptions
import com.parent.entities.TimeFormat
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 11/28/17.
 */
class InstituteOptionsRepository(private val remoteDataSource: InstituteOptionsDataSource,
                                 private val localDataSource: InstituteOptionsDataSource) :
        BaseRepository(), InstituteOptionsDataSource {
    override fun loadInstituteOptions(instituteId: String): Single<InstituteOptions> =
            when {
                isCacheInvalid() -> loadRemoteOptions(instituteId)
                else -> loadLocalOptions(instituteId)
            }

    override fun updateInstituteOptions(options: InstituteOptions): Completable =
            remoteDataSource.updateInstituteOptions(options).doOnComplete {
                saveInstituteOptions(options).blockingAwait()
            }

    override fun saveInstituteOptions(options: InstituteOptions): Completable =
            localDataSource.saveInstituteOptions(options)

    override fun deleteInstituteOptions(instituteId: String): Completable =
            localDataSource.deleteInstituteOptions(instituteId)

    override fun invalidateCache() {
        setCacheValid(false)
    }

    override fun loadTimeFormats(): Single<List<TimeFormat>> =
            localDataSource.loadTimeFormats()

    override fun loadDateFormats(): Single<List<DateFormat>> =
            localDataSource.loadDateFormats()

    override fun loadWeekStarts(): Single<List<WeekStart>> =
            localDataSource.loadWeekStarts()

    private fun loadRemoteOptions(instituteId: String) =
            remoteDataSource.loadInstituteOptions(instituteId).doOnSuccess {
                saveInstituteOptions(it).doOnComplete { setCacheValid(true) }.blockingAwait()
            }

    private fun loadLocalOptions(instituteId: String) =
            localDataSource.loadInstituteOptions(instituteId).onErrorResumeNext {
                when (it) {
                    is NoSuchElementException -> loadRemoteOptions(instituteId)
                    else -> Single.error(it)
                }
            }
}

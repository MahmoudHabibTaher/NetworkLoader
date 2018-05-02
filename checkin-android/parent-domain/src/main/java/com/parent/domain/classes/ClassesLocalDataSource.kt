package com.parent.domain.classes

import com.parent.entities.ClassModel
import com.parent.entities.ClassDetails
import com.parent.entities.Status
import com.parent.entities.exceptions.EmptyListException
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 12/6/17.
 */
class ClassesLocalDataSource(private val classesDao: ClassesDao) : ClassesDataSource {
    override fun loadClass(id: String): Single<ClassDetails> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadDashboardClasses(instituteId: String): Single<List<ClassModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadClasses(instituteId: String): Single<List<ClassModel>> =
            classesDao.loadClasses(instituteId).flatMap {
                if (it.isNotEmpty()) Single.just(it) else Single.error(EmptyListException(""))
            }

    override fun loadUserClasses(instituteId: String, userId: String): Single<List<ClassModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveClasses(instituteId: String, classes: List<ClassModel>): Completable =
            classesDao.saveClasses(instituteId, classes)

    override fun deleteClasses(instituteId: String): Completable =
            classesDao.deleteClasses(instituteId)


    override fun loadClassStatuses(classId: String): Single<List<Status>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadClassStatusDetails(classId: String, statusType: String): Single<ClassDetails> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadClassSettings(classId: String): Single<ClassModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun invalidateCache() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
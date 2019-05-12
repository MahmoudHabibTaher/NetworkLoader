package com.parent.domain.children.data

import com.vicpin.krealmextensions.*
import com.parent.entities.ChildModel
import com.parent.entities.ChildPickupModel
import com.parent.entities.ClassChild
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.base.mappers.mapToWith
import com.parent.domain.realm.entities.*
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 12/8/17.
 */
class ChildrenRealmDao(private val modelMapper: ModelMapper<ClassChildRealm, ClassChild>,
                       private val childRealmModelMapper: ModelMapper<ChildModelRealm, ChildModel>,
                       private val dailyReportRealmModelMapper: ModelMapper<DailyReportRealm, DailyReport>,
                       private val childLeavesRealmModelMapper: ModelMapper<ChildLeavesRealm, ChildLeaves>,
                       private val childHealthRealmeModelMapper: ModelMapper<ChildHealthModelRealm, ChildHealthModel>,
                       private val childChildPickupModelRealmeModelMapper: ModelMapper<ChildPickupModelRealm, ChildPickupModel>,
                       private val childPermissionRealmModelMapper: ModelMapper<ChildPermissionRealm, ChildPermission>) : ChildrenDao {
    override fun loadClassChildren(classId: String): Single<List<ClassChild>> =
            Single.fromCallable {
                ClassChildRealm().query { it.equalTo("classId", classId) } mapFromWith modelMapper
            }

    override fun loadChildDetails(childId: String): Single<ChildModel> =
            Single.fromCallable {
                childRealmModelMapper.mapFrom(ChildModelRealm().queryFirst() { it.equalTo("id", childId) } ?: ChildModelRealm())
            }

    override fun loadChildHealthDetails(childId: String, institutionId: String): Single<ChildHealthModel> =
            Single.fromCallable {
                childHealthRealmeModelMapper.mapFrom(ChildHealthModelRealm().queryFirst() { it.equalTo("id", childId) } ?: ChildHealthModelRealm())
            }

    override fun loadChildPickUp(childId: String): Single<ChildPickupModel> =
            Single.fromCallable {
                childChildPickupModelRealmeModelMapper.mapFrom(ChildPickupModelRealm().queryFirst() { it.equalTo("childId", childId) } ?: ChildPickupModelRealm())
            }

    override fun saveClassChildren(classId: String, children: List<ClassChild>): Completable =
            Completable.fromAction {
                val list = children mapToWith modelMapper
                list.forEach {
                    it.classId = classId
                }
                list.saveAll()
            }

    override fun deleteClassChildren(classId: String): Completable =
            Completable.fromAction {
                ClassChildRealm().delete { it.equalTo("classId", classId) }
            }

    override fun loadChildLeaves(childId: String): Single<ChildLeaves> =
            Single.fromCallable {
                ChildLeavesRealm().queryFirst { it.equalTo("childId", childId) }
                        ?.mapFromWith(childLeavesRealmModelMapper) ?: ChildLeaves()
            }

    override fun saveChildLeaves(childId: String, childLeaves: ChildLeaves): Completable =
            Completable.fromAction {
                childLeaves.mapToWith(childLeavesRealmModelMapper).apply {
                    this.childId = childId
                }.save()
            }

    override fun deleteChildLeaves(childId: String): Completable =
            Completable.fromAction {
                ChildLeavesRealm().delete { it.equalTo("childId", childId) }
            }

    override fun loadChildDailyReport(childId: String, date: Long): Single<DailyReport> =
            Single.fromCallable {
                val report = DailyReportRealm().queryFirst {
                    it.equalTo("childId", childId)
                            .equalTo("date", date)
                }?.mapFromWith(dailyReportRealmModelMapper) ?: throw NoSuchElementException("No report found for $childId on $date")

                report
            }

    override fun saveChildDailyReport(dailyReport: DailyReport): Completable =
            Completable.fromAction {
                dailyReport.mapToWith(dailyReportRealmModelMapper).save()
            }

    override fun deleteChildDailyReport(childId: String, date: Long): Completable =
            Completable.fromAction {
                DailyReportRealm().delete {
                    it.equalTo("childId", childId)
                            .equalTo("date", date)
                }
            }

    override fun loadChildPermissions(childId: String): Single<List<ChildPermission>> =
            Single.fromCallable {
                ChildPermissionRealm().query { it.equalTo("childId", childId) }
                        .mapFromWith(childPermissionRealmModelMapper)
            }

    override fun saveChildPermissions(childId: String, permissions: List<ChildPermission>): Completable =
            Completable.fromAction {
                permissions.mapToWith(childPermissionRealmModelMapper)
                        .map {
                            it.childId = childId
                            it
                        }.saveAll()
            }

    override fun deleteChildPermissions(childId: String): Completable =
            Completable.fromAction {
                ChildPermissionRealm().delete { it.equalTo("childId", childId) }
            }

}
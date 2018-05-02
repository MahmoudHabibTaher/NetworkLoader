package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import com.parent.domain.realm.base.toRealmList
import io.realm.RealmList
import io.realm.RealmObject
import org.joda.time.LocalDate

/**
 * Created by mahmoud on 12/25/17.
 */
open class DailyReportRealm(var childId: String = "",
                            var date: Long = -1,
                            var items: RealmList<ReportItemRealm> = RealmList()) : RealmObject() {
    class Builder : IBuilder<DailyReportRealm> {
        private var childId = ""
        private var date = -1L
        private var items = RealmList<ReportItemRealm>()

        fun childId(childId: String): Builder {
            this.childId = childId
            return this
        }

        fun date(date: Long): Builder {
            this.date = date
            return this
        }

        fun items(items: RealmList<ReportItemRealm>): Builder {
            this.items = items
            return this
        }

        override fun build(): DailyReportRealm =
                DailyReportRealm(childId, date, items)
    }

    class TestBuilder {
        companion object {
            fun buildDailyReport() =
                    Builder().childId("1").date(LocalDate.now().toDateTimeAtStartOfDay().millis)
                            .items(ReportItemRealm.TestBuilder.buildList().toRealmList())
                            .build()
        }
    }
}
package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import com.parent.entities.Status
import io.realm.RealmObject
import org.joda.time.DateTime

/**
 * Created by mahmoud on 12/25/17.
 */
open class ReportItemRealm(var type: String = "",
                           var time: Long = -1,
                           var data: String = "") : RealmObject() {
    class Builder : IBuilder<ReportItemRealm> {
        private var type = ""
        private var time = -1L
        private var data = ""

        fun type(type: String): Builder {
            this.type = type
            return this
        }

        fun time(time: Long): Builder {
            this.time = time
            return this
        }

        fun data(data: String): Builder {
            this.data = data
            return this
        }

        override fun build(): ReportItemRealm =
                ReportItemRealm(type, time, data)
    }

    class TestBuilder {
        companion object {
            fun buildReportItem(type: String) =
                    Builder().type(type)
                            .time(DateTime.now().withTime(8, 0, 0, 0).millis)
                            .data("Report item")
                            .build()

            fun buildList() =
                    listOf(buildReportItem(Status.Type.CHECK_IN))
        }
    }
}
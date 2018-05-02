package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import com.parent.domain.realm.base.toRealmList
import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by mahmoud on 12/22/17.
 */
open class YearLeavesRealm(var year: Int = 0,
                           var months: RealmList<MonthLeavesRealm> = RealmList()) : RealmObject() {
    class Builder : IBuilder<YearLeavesRealm> {
        private var year = 0
        private var months = RealmList<MonthLeavesRealm>()

        fun year(year: Int): Builder {
            this.year = year
            return this
        }

        fun months(months: RealmList<MonthLeavesRealm>): Builder {
            this.months = months
            return this
        }

        override fun build(): YearLeavesRealm =
                YearLeavesRealm(year, months)
    }

    class TestBuilder {
        companion object {
            fun builYearLeaves() =
                    Builder().year(2017).months(MonthLeavesRealm.TestBuilder.buildList().toRealmList()).build()

            fun buildList() =
                    listOf(builYearLeaves())
        }
    }
}
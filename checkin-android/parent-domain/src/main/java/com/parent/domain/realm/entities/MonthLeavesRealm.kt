package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import com.parent.domain.realm.base.toRealmList
import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by mahmoud on 12/22/17.
 */
open class MonthLeavesRealm(var month: String = "",
                            var leaves: RealmList<LeaveRealm> = RealmList()) : RealmObject() {
    class Builder : IBuilder<MonthLeavesRealm> {
        private var month = ""
        private var leaves = RealmList<LeaveRealm>()

        fun month(month: String): Builder {
            this.month = month
            return this
        }

        fun leaves(leaves: RealmList<LeaveRealm>): Builder {
            this.leaves = leaves
            return this
        }

        override fun build(): MonthLeavesRealm =
                MonthLeavesRealm(month, leaves)
    }

    class TestBuilder {
        companion object {
            fun buildMonthLeaves() =
                    Builder().month("January").leaves(LeaveRealm.TestBuilder.buildList().toRealmList()).build()

            fun buildList() =
                    listOf(buildMonthLeaves())
        }
    }
}
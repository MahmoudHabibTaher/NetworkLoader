package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import com.parent.domain.realm.base.toRealmList
import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by mahmoud on 12/22/17.
 */
open class ChildLeavesRealm(var childId: String = "",
                            var years: RealmList<YearLeavesRealm> = RealmList()) : RealmObject() {
    class Builder : IBuilder<ChildLeavesRealm> {
        private var childId = ""
        private var years = RealmList<YearLeavesRealm>()

        fun childId(childId: String): Builder {
            this.childId = childId
            return this
        }

        fun years(years: RealmList<YearLeavesRealm>): Builder {
            this.years = years
            return this
        }

        override fun build(): ChildLeavesRealm =
                ChildLeavesRealm(childId, years)
    }

    class TestBuilder {
        companion object {
            fun buildChildLeaves() =
                    Builder().childId("1").years(YearLeavesRealm.TestBuilder.buildList().toRealmList()).build()
        }
    }
}
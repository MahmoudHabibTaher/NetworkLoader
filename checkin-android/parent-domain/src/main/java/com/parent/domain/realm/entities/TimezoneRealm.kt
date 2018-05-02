package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by mahmoud on 11/28/17.
 */
open class TimezoneRealm(@PrimaryKey var key: String = "",
                         var value: String = "") : RealmObject() {
    class Builder : IBuilder<TimezoneRealm> {
        private var key = ""
        private var value = ""

        fun key(key: String): Builder {
            this.key = key
            return this
        }

        fun value(value: String): Builder {
            this.value = value
            return this
        }

        override fun build(): TimezoneRealm =
                TimezoneRealm(key, value)
    }

    class TestBuilder {
        companion object {
            fun buildNormalTimezone() =
                    Builder().key("UTC")
                            .value("(UTC -00:00) UTC")
                            .build()

            fun buildList() =
                    listOf(buildNormalTimezone())
        }
    }
}
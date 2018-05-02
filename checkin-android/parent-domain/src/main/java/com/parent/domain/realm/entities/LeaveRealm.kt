package com.parent.domain.realm.entities

import com.parent.entities.Leave
import com.parent.entities.IBuilder
import io.realm.RealmObject

/**
 * Created by mahmoud on 12/22/17.
 */
open class LeaveRealm(var type: String = "",
                      var value: Int = 0) : RealmObject() {
    class Builder : IBuilder<LeaveRealm> {
        private var type = ""
        private var value = 0

        fun type(type: String): Builder {
            this.type = type
            return this
        }

        fun value(value: Int): Builder {
            this.value = value
            return this
        }

        override fun build(): LeaveRealm =
                LeaveRealm(type, value)
    }

    class TestBuilder {
        companion object {
            fun buildLeave(type: String) =
                    Builder().type(type).value(10).build()

            fun buildList() =
                    listOf(buildLeave(Leave.Type.SICK))
        }
    }
}
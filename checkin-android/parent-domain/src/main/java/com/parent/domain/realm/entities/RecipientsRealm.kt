package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import io.realm.RealmObject

/**
 * Created by raede on 26/10/2017.
 */
open class RecipientsRealm(
        var dayItemId: String = "",
        var id: String = "",
        var name: String = "",
        var type: String = "") : RealmObject() {
    class TestBuilder : IBuilder<RecipientsRealm> {
        companion object {
            private val DAY_ITEM_IDS = listOf("1", "2", "3", "4")
            private val IDS = listOf("1", "2", "3", "4")
            private val NAMES = listOf("USER 1", "USER 2", "USER 3", "USER 4")
            private val TYPES = listOf("TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4")

            fun buildValidRecipientsRealm() =
                    TestBuilder().dayItemId("1").id("1")
                            .name("USER 1")
                            .type("TYPE 1")
                            .build()

            fun buildList(): List<RecipientsRealm> =
                    IDS.mapIndexed { index, id ->
                        TestBuilder().dayItemId(DAY_ITEM_IDS[index]).id(id).name(NAMES[index]).type(TYPES[index]).build()
                    }
        }

        private var dayItemId: String? = ""
        private var id: String? = ""
        private var name: String? = ""
        private var type: String? = ""

        fun dayItemId(dayItemId: String?): TestBuilder {
            this.dayItemId = dayItemId
            return this
        }

        fun id(id: String?): TestBuilder {
            this.id = id
            return this
        }

        fun name(name: String?): TestBuilder {
            this.name = name
            return this
        }

        fun type(type: String?): TestBuilder {
            this.type = type
            return this
        }

        override fun build(): RecipientsRealm =
                RecipientsRealm(dayItemId ?: "", id ?: "", name ?: "", type ?: "")
    }
}
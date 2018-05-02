package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import io.realm.RealmObject

/**
 * Created by mahmoud on 11/28/17.
 */
open class CityRealm(var id: String = "", var name: String = "") : RealmObject() {
    class Builder : IBuilder<CityRealm> {
        private var id = ""
        private var name = ""

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        override fun build(): CityRealm =
                CityRealm(id, name)
    }

    class TestBuilder {
        companion object {
            fun buildNormalCity() =
                    Builder().id("1").name("Cairo").build()

            fun buildList() =
                    listOf(buildNormalCity())
        }
    }
}
package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import io.realm.RealmObject

/**
 * Created by mahmoud on 11/28/17.
 */
open class NationalityRealm(var id: String = "",
                            var name: String = ""
                            ) : RealmObject() {
    class Builder : IBuilder<NationalityRealm> {
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

        override fun build(): NationalityRealm =
                NationalityRealm(id, name)
    }

    class TestBuilder {
        companion object {
            fun buildNormalNationality() =
                    Builder().id("1")
                            .name("Egyptian")
                            .build()

            fun normalCountrWithCities() =
                    Builder().id("1")
                            .name("Egyptian")
                            .build()

            fun buildList() =
                    listOf(buildNormalNationality())
        }
    }
}
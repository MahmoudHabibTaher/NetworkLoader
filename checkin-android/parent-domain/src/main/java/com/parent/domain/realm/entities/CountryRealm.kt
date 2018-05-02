package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import com.parent.domain.realm.base.toRealmList
import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by mahmoud on 11/28/17.
 */
open class CountryRealm(var id: String = "",
                        var name: String = "",
                        var code: String = "",
                        var cities: RealmList<CityRealm> = RealmList()) : RealmObject() {
    class Builder : IBuilder<CountryRealm> {
        private var id = ""
        private var name = ""
        private var code = ""
        private var cities = RealmList<CityRealm>()

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun code(code: String): Builder {
            this.code = code
            return this
        }

        fun cities(cities: RealmList<CityRealm>): Builder {
            this.cities = cities
            return this
        }

        override fun build(): CountryRealm =
                CountryRealm(id, name, code)
    }

    class TestBuilder {
        companion object {
            fun buildNormalCountry() =
                    Builder().id("1")
                            .name("Egypt")
                            .code("EG")
                            .build()

            fun normalCountrWithCities() =
                    Builder().id("1")
                            .name("Egypt")
                            .code("EG")
                            .cities(CityRealm.TestBuilder.buildList().toRealmList())
                            .build()

            fun buildList() =
                    listOf(buildNormalCountry())
        }
    }
}
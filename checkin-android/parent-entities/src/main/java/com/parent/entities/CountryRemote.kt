package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 11/28/17.
 */
data class CountryRemote(@SerializedName("id") val id: String? = "",
                         @SerializedName("name") val name: String? = "",
                         @SerializedName("code") val code: String? = "",
                         @SerializedName("cities") val cities: List<CityRemote>? = emptyList()) {
    class Builder : IBuilder<CountryRemote> {
        private var id: String? = ""
        private var name: String? = ""
        private var code: String? = ""
        private var cities: List<CityRemote>? = emptyList()

        fun id(id: String?): Builder {
            this.id = id
            return this
        }

        fun name(name: String?): Builder {
            this.name = name
            return this
        }

        fun code(code: String?): Builder {
            this.code = code
            return this
        }

        fun cities(cities: List<CityRemote>?): Builder {
            this.cities = cities
            return this
        }

        override fun build(): CountryRemote =
                CountryRemote(id, name, code, cities)
    }

    class TestBuilder {
        companion object {
            fun buildNormalCountry() =
                    Builder().id("1")
                            .name("Egypt")
                            .code("EG")
                            .build()

            fun buildNormalCountryWithCities() =
                    Builder().id("1")
                            .name("Egypt")
                            .code("EG")
                            .cities(CityRemote.TestBuilder.buildList())
                            .build()

            fun buildNullFieldsCountry() =
                    Builder().id(null)
                            .name(null)
                            .code(null)
                            .build()

            fun buildList() =
                    listOf(buildNormalCountry())
        }
    }
}
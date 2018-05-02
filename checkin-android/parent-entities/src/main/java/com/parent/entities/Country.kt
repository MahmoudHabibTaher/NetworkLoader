package com.parent.entities

/**
 * Created by mahmoud on 11/27/17.
 */
data class Country(val id: String = "", val name: String = "", val code: String = "", val cities: List<City> = listOf()) {
    class Builder : IBuilder<Country> {
        private var id = ""
        private var name = ""
        private var code = ""
        private var cities = listOf<City>()

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

        fun cities(cities: List<City>): Builder {
            this.cities = cities
            return this
        }

        override fun build(): Country =
                Country(id, name, code, cities)
    }

    class TestBuilder {
        companion object {
            fun buildNormalCountry() =
                    Builder().id("1")
                            .name("Egypt")
                            .code("EG")
                            .cities(City.TestBuilder.buildList())
                            .build()

            fun buildList() =
                    listOf(buildNormalCountry())
        }
    }
}
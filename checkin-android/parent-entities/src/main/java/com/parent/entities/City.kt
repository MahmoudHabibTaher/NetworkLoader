package com.parent.entities

/**
 * Created by mahmoud on 11/28/17.
 */
data class City(val id: String = "", val name: String = "") {
    class Builder : IBuilder<City> {
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

        override fun build(): City =
                City(id, name)
    }

    class TestBuilder {
        companion object {
            fun buildNormalCity() =
                    Builder().id("1")
                            .name("Cairo")
                            .build()

            fun buildList() =
                    listOf(buildNormalCity())
        }
    }
}
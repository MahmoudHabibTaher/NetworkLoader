package com.parent.entities

/**
 * Created by mahmoud on 11/27/17.
 */
data class Nationality(val id: String = "", val name: String = "") {
    class Builder : IBuilder<Nationality> {
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

        override fun build(): Nationality =
                Nationality(id, name)
    }

    class TestBuilder {
        companion object {
            fun buildNormalCountry() =
                    Builder().id("1")
                            .name("Egyptian")
                            .build()

            fun buildList() =
                    listOf(buildNormalCountry())
        }
    }
}
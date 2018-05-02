package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 11/28/17.
 */
data class CityRemote(@SerializedName("id") val id: String? = "",
                      @SerializedName("name") val name: String? = "") {
    class Builder : IBuilder<CityRemote> {
        private var id: String? = ""
        private var name: String? = ""

        fun id(id: String?): Builder {
            this.id = id
            return this
        }

        fun name(name: String?): Builder {
            this.name = name
            return this
        }

        override fun build(): CityRemote =
                CityRemote(id, name)
    }

    class TestBuilder {
        companion object {
            fun buildNormalCity() =
                    Builder().id("1")
                            .name("Cairo")
                            .build()

            fun buildInvalidCity() =
                    Builder().id(null)
                            .name(null)
                            .build()

            fun buildList() =
                    listOf(buildNormalCity())
        }
    }
}
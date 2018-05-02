package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 11/28/17.
 */
data class NationalityRemote(@SerializedName("id") val id: String? = "",
                             @SerializedName("nationality_name") val nationalityName: String? = null,
                             @SerializedName("name") val name: String? = null) {
    class Builder : IBuilder<NationalityRemote> {
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

        override fun build(): NationalityRemote =
                NationalityRemote(id, name)
    }

    class TestBuilder {
        companion object {
            fun buildNormalNationality() =
                    Builder().id("1")
                            .name("Egyptian")
                            .build()

            fun buildNormalNationalityWithCities() =
                    Builder().id("1")
                            .name("Egyptian")
                            .build()

            fun buildNullFieldsNationality() =
                    Builder().id(null)
                            .name(null)
                            .build()

            fun buildList() =
                    listOf(buildNormalNationality())
        }
    }
}
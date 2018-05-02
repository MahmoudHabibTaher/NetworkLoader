package com.parent.entities

import com.google.gson.annotations.SerializedName

class InstituteMealRemote(
        @SerializedName("id") val id: Int? = 0,
        @SerializedName("name") val name: String? = "") {
    class Builder : IBuilder<InstituteMealRemote> {
        private var id = 0
        private var name = ""


        fun id(id: Int): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }


        override fun build(): InstituteMealRemote =
                InstituteMealRemote(id, name)

    }

    class TestBuilder {
        companion object {
            fun buildInstituteMeal() =
                    Builder().id(0).name("Name")
                            .build()
        }
    }

}
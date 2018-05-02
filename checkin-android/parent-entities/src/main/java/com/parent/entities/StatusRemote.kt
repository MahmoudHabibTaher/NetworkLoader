package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by ahmedmahmoud on 2/19/18.
 */
class StatusRemote(@SerializedName("type") val type: String? = "",
                   @SerializedName("name") val name: String? = "",
                   @SerializedName("total") val total: Int? = 0) {

    class Builder : IBuilder<StatusRemote> {
        private var type: String = ""
        private var name: String = ""
        private var total: Int = 0


        fun type(type: String): Builder {
            this.type = type
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun total(total: Int): Builder {
            this.total = total
            return this
        }


        override fun build(): StatusRemote =
                StatusRemote(type, name, total)
    }

    class TestBuilder {
        companion object {
            fun buildValidStatusRemote() =
                    Builder().type("check_in")
                            .name("Checked In")
                            .total(10)
                            .build()

            fun buildEmptyStatusRemote() =
                    Builder().type("")
                            .name("")
                            .total(0)
                            .build()
        }
    }
}
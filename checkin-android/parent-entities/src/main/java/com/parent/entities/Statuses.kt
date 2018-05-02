package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by ahmedmahmoud on 3/4/18.
 */
class Statuses(@SerializedName("statuses") val status: List<StatusRemote>?= listOf()) {


    class Builder : IBuilder<Statuses> {

        private var status: List<StatusRemote>?= listOf()


        fun status(status: List<StatusRemote>?): Builder {
            this.status = status
            return this
        }
        override fun build(): Statuses =
                Statuses(status)
    }

    class TestBuilder {
        companion object {
            fun buildNormalClass() =
                    Builder()
                            .status(listOf())
                            .build()

            fun buildList() =
                    listOf(buildNormalClass())
        }
    }

}


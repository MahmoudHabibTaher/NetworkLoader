package com.parent.domain.realm.entities

import io.realm.RealmObject

/**
 * Created by raede on 10/12/2017.
 */
open class HolidayComingChildModelRealm(

        var className: String = "",
        var name: String = "",
        var image: String = ""
):RealmObject() {
    class TestBuilder {
        companion object {
            fun buildNormalHolidayComingChildModelRealm() =
                    HolidayComingChildModelRealm("Ants", "Toiny", "")
        }
    }
}
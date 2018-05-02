package com.parent.domain.realm.entities

import io.realm.RealmList
import io.realm.RealmObject


/**
 * Created by raede on 10/12/2017.
 */
open class HolidayComingGroupModelRealm(

        var name: String = "",
        var image: String = "",
        var coming: String = "",
        var comingChildren: RealmList<HolidayComingChildModelRealm> = RealmList()
) : RealmObject() {
    class TestBuilder {
        companion object {
            fun buildNormalHolidayComingGroupModel() =
                    HolidayComingGroupModelRealm("Ants", "",
                            "12", RealmList(HolidayComingChildModelRealm.TestBuilder.buildNormalHolidayComingChildModelRealm()))
        }
    }
}
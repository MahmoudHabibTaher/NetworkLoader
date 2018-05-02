package com.parent.domain.realm.entities

import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by Raed Ezzat on 14/07/2017.
 */
open class NewsFeedModelRealm(
        var items: RealmList<NewsFeedItemModelRealm> = RealmList(),
        var currentPage: String = ""

):RealmObject()
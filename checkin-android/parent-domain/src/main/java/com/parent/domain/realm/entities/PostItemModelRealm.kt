package com.parent.domain.realm.entities

import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by Raed Ezzat on 15/01/2018.
 */
open class PostItemModelRealm(
        var body: String = "",
        var dateTime: Long = 0,
        var dateTimeText: String = "",
        var recipients: RealmList<RecipientRealm>? = RealmList(),
        var media: RealmList<MediaModelRealm>? = RealmList(),
        var totalLikes: Int = 0,
        var totalComments: Int = 0,
        var seenByCount: Int = 0
) : RealmObject() {}
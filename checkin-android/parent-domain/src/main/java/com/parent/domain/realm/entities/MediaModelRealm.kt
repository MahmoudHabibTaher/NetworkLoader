package com.parent.domain.realm.entities

import io.realm.RealmObject

/**
 * Created by Raed Ezzat on 15/01/2018.
 */
open class MediaModelRealm(
        var id: String = "",
        var name: String = "",
        var url: String = "",
        var fileType: Int = 0,
        var mimeType: String = "",
        var thumbnailUrl: String = ""
):RealmObject() {
}
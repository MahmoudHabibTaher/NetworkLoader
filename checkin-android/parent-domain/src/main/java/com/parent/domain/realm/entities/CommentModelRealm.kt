package com.parent.domain.realm.entities

import io.realm.RealmObject

/**
 * Created by Raed Ezzat on 15/01/2018.
 */
open class CommentModelRealm(
        var id: String = "",
        var body: String = "",
        var totalLikes: Int = 0,
        var commentDateTime: Long =0,
        var commentDateTimeText: String = "",
        var user: UserModelRealm? = UserModelRealm(),
        var isCreator: Boolean = false,
        var canDelete: Boolean = false,
        var isLike: Boolean = false,

        var currentPage: Int = 0,
        var firstPageUrl: String = "",
        var from: String = "",
        var lastPage: Int = 0,
        var path: String = "",
        var perPage: String = "",
        var to: String = "",
        var total: String = ""
):RealmObject() {
}
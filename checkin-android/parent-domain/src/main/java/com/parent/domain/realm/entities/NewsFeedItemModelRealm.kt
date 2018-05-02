package com.parent.domain.realm.entities

import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by Raed Ezzat on 15/01/2018.
 */
open class NewsFeedItemModelRealm(

        //pageModel

        var currentPage: String = "",
        var firstPageUrl: String = "",
        var from: String = "",
        var lastPage: String = "",

        var path: String = "",
        var perPage: String = "",
        var to: String = "",
        var total: String = "",

        //NotificationModel

        var id: String = "",
        var type: String = "",
        var canEdit: Boolean = false,
        var canDelete: Boolean = false,
        var isLiked: Boolean = false,
        var isCreator: Boolean = false,
        var seenByCount: Int = 0,
        var canLike: Boolean = false,
        var canComment: Boolean = false,
        var user: UserModelRealm? = UserModelRealm(),
        var author: AuthorModelRealm? = AuthorModelRealm(),
        var entityName: String = "",
        var entityTitle: String = "",
        var actionText: String = "",
        var loadMore: Boolean = false,
        var latestComments: RealmList<CommentModelRealm>? = RealmList(),
        var event: EventModelRealm? = EventModelRealm(),
        var holiday: HolidayRealm? = HolidayRealm(),
        var newsfeed: PostItemModelRealm? = PostItemModelRealm(),
        var status: UserStatusItemModelRealm? = UserStatusItemModelRealm()
) : RealmObject()
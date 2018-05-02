package com.parent.entities

import com.parent.entities.ChildModel

/**
 * Created by Raed Ezzat on 15/01/2018.
 */
class UserStatusItemModel(
        var body: String = "",
        var type: String = "",
        var dateTime: Long = 0,
        var dateTimeText: String = "",
        var child: ChildModel = ChildModel()


)
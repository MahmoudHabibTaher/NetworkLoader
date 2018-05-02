package com.parent.entities

/**
 * Created by Raed Ezzat on 15/01/2018.
 */
class UserStatusItemModelView(
        var body: String = "",
        var type: Int = 0,
        var dateTime: Long = 0,
        var dateTimeText: String = "",
        var child: ChildModelView = ChildModelView()


)
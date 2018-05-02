package com.parent.entities

/**
 * Created by Raed Ezzat on 17/03/2018.
 */
class NavigationEntityTypes(var type: Int=0, var action: Int=0, var itemId: String="", var subId: String="") {

    companion object {
        val TYPE_NOT_IMPLEMENTED = -1
        val TYPE_ACTIVITY = 0
        val TYPE_EVENT = 1
        val TYPE_HOLIDAY = 2
        val TYPE_NEWSFEED = 3

        val ACTION_DEFAULT = -1
        val ACTION_CREATE = 0
        val ACTION_REMINDER = 1
        val ACTION_REPLY = 2
        val ACTION_EDIT = 3
        val ACTION_DELETE = 4


    }

}
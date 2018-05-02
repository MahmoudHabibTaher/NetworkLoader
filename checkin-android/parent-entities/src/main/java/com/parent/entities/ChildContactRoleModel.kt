package com.parent.entities

/**
 * Created by Raed Ezzat on 05/01/2018.
 */
class ChildContactRoleModel(
        var id: String = "",
        var title: String = "",
        var description: String = ""
){
    companion object {
        var PARENT_MODEL_ROLE="1"
    }
}
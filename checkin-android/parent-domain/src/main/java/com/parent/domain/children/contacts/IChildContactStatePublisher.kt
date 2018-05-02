package com.parent.domain.children.contacts

/**
 * Created by mahmoud on 1/4/18.
 */
interface IChildContactStatePublisher {
    fun notifyChildContactAddedSuccefully(state: Boolean)
    fun notifyChildContactEditedSuccefully(state: Boolean)
}
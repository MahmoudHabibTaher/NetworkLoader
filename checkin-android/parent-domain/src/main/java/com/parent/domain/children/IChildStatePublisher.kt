package com.parent.domain.children

import com.parent.entities.ChildState

/**
 * Created by mahmoud on 1/4/18.
 */
interface IChildStatePublisher {
    fun notifyState(state: ChildState)
}
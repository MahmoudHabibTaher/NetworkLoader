package com.parent.domain.main

/**
 * Created by mahmoud on 1/18/18.
 */
interface MainActivityStatePublisher {
    fun notifyChildrenAvailableState(state:Boolean)
}
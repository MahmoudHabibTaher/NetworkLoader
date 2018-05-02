package com.parent.entities

/**
 * Created by mahmoud on 10/16/17.
 */
interface IBuilder<out T> {
    fun build(): T
}
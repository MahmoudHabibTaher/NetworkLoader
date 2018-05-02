package com.parent.domain.auth

/**
 * Created by mahmoud on 1/19/18.
 */
interface ISessionStateManager {
    fun shouldRenewSession(): Boolean

    fun sessionRenewStart()

    fun sessionRenewSuccess()

    fun sessionRenewError()
}
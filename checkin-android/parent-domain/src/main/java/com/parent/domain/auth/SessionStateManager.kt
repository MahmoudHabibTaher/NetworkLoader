package com.parent.domain.auth

import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by mahmoud on 1/19/18.
 */
object SessionStateManager : ISessionStateManager {
    private val renewCount = AtomicInteger(0)

    override fun shouldRenewSession(): Boolean = renewCount.get() == 0

    override fun sessionRenewStart() {
        renewCount.addAndGet(1)
    }

    override fun sessionRenewSuccess() {
        renewCount.set(0)
    }

    override fun sessionRenewError() {

    }
}
package com.parent.domain.common.presentation

import com.parent.entities.exceptions.NotAuthenticatedException
import com.parent.entities.exceptions.PermissionsChangedException

/**
 * Created by mahmoud on 10/27/17.
 */
class GenericErrorHandlerView(var onError: (Int, String) -> Unit = { _, _ -> }) : ErrorHandlerView {
    override fun handleError(throwable: Throwable): Boolean = when (throwable) {
        is NotAuthenticatedException -> {
            handleSessionExpired(throwable.message)
            true
        }
        is PermissionsChangedException -> {
            handlePermissionsChanged(throwable.message)
            true
        }
        else -> false
    }

    private fun handleSessionExpired(message: String?) {
        onError(GenericErrors.ERR_SESSION_EXPIRED, message ?: "")
    }

    private fun handlePermissionsChanged(message: String?) {
        onError(GenericErrors.ERR_PERMISSIONS_CHANGED, message ?: "")
    }
}
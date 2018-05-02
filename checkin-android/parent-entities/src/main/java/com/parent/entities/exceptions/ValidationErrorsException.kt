package com.parent.entities.exceptions

import android.util.Log
import com.parent.entities.ValidationErrorRemote

/**
 * Created by mahmoud on 11/22/17.
 */
class ValidationErrorsException(message: String? = "", val validationErrors: List<ValidationErrorRemote>) : Exception(message) {
    fun getErrorMessage(): String {
        var message = ""
        var validationErrorsException = this
        if (validationErrorsException.validationErrors.isNotEmpty()) {
            for (error in validationErrorsException.validationErrors) {
                message = error.errorMsg
                Log.e("message", "" + error.errorMsg)
            }
        }
        Log.e("all message", "" + message)
        return message
    }
}
package com.parent.entities

/**
 * Created by mahmoud on 11/22/17.
 */
class ValidationErrorsResponse(status: String?, message: String?, data: List<ValidationErrorRemote>?) :
        BaseErrorResponse<List<ValidationErrorRemote>>(status, message, data)
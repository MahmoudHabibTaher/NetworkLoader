package com.parent.entities.exceptions

/**
 * Created by mahmoud on 9/15/17.
 */
class ResetLinkInvalidException(message: String, var email: String) : Exception(message)
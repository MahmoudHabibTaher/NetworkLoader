package com.parent.entities.exceptions

/**
 * Created by mahmoud on 9/14/17.
 */
class ResetLinkExpiredException(message: String, var email: String) : Exception(message)
package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 1/2/18.
 */
data class PermissionReplyRequest(@SerializedName("contact_id") var contactId: String,
                                  @SerializedName("reply_status") var replyStatus: String)
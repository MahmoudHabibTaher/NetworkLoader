package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 12/30/17.
 */
data class PermissionReplyRemote(@SerializedName("msg") val message: String? = "",
                                 @SerializedName("who_replied") val whoReplied: String? = "",
                                 @SerializedName("reply_status") val status: String? = Status.NO_REPLY,
                                 @SerializedName("reply_date") val date: String? = "") {
    object Status {
        const val NO_REPLY = "no_reply"
        const val YES = "Yes"
        const val NO = "No"
    }

    class TestBuilder {
        companion object {
            fun buildPermissionReply() =
                    PermissionReplyRemote("Reply message", "Mahmoud Habib",
                            Status.YES, "12/12/2017 08:00:00")

            fun buildPermissionReplyYesStatus() =
                    PermissionReplyRemote("Reply message", "Mahmoud Habib",
                            Status.YES, "12/12/2017 08:00:00")

            fun buildPermissionReplyNoStatus() =
                    PermissionReplyRemote("Reply message", "Mahmoud Habib",
                            Status.NO, "12/12/2017 08:00:00")

            fun buildEmptyPermissionReply() =
                    PermissionReplyRemote(null, null,
                            null, null)

            fun buildList() =
                    listOf(buildPermissionReply())

        }
    }
}
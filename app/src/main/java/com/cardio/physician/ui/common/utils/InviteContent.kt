package com.cardio.physician.ui.common.utils

import android.net.Uri

data class InviteContent(
    /** The subject of the message. Not used for invites without subjects, like SMS.  */
    val subject: String?,
    /** The body of the message. Indispensable content should go here.  */
    val body: String?,
    /** The URL containing the link to invite. In link-copy cases, only this field will be used.  */
    val link: Uri
)

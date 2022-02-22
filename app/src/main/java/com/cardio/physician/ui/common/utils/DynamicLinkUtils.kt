package com.cardio.physician.ui.common.utils

import android.net.Uri
import com.cardio.physician.BuildConfig
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks

object DynamicLinkUtils {

    fun generateContentLink(userId:String): Uri {
        val baseUrl = Uri.parse("https://pocketcadevapi.appskeeper.in//?doctorId=${userId}")
        val link = FirebaseDynamicLinks.getInstance()
            .createDynamicLink()
            .setLink(baseUrl)
            .setDomainUriPrefix(BuildConfig.DOMAIN)
            .setIosParameters(DynamicLink.IosParameters.Builder("com.pocketcardiodoctor.beta").build())
            .setAndroidParameters(DynamicLink.AndroidParameters.Builder(BuildConfig.PATINET_PACKAGE_ANME).build())
            .buildDynamicLink()

        return link.uri
    }
}
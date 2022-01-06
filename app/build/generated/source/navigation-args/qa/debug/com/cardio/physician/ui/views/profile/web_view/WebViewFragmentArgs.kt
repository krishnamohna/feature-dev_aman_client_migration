package com.cardio.physician.ui.views.profile.web_view

import android.os.Bundle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class WebViewFragmentArgs(
  public val toolBarTitle: String?,
  public val webUrl: String?
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("toolBarTitle", this.toolBarTitle)
    result.putString("webUrl", this.webUrl)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): WebViewFragmentArgs {
      bundle.setClassLoader(WebViewFragmentArgs::class.java.classLoader)
      val __toolBarTitle : String?
      if (bundle.containsKey("toolBarTitle")) {
        __toolBarTitle = bundle.getString("toolBarTitle")
      } else {
        throw IllegalArgumentException("Required argument \"toolBarTitle\" is missing and does not have an android:defaultValue")
      }
      val __webUrl : String?
      if (bundle.containsKey("webUrl")) {
        __webUrl = bundle.getString("webUrl")
      } else {
        throw IllegalArgumentException("Required argument \"webUrl\" is missing and does not have an android:defaultValue")
      }
      return WebViewFragmentArgs(__toolBarTitle, __webUrl)
    }
  }
}

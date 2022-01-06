package com.cardio.physician.ui.views.profile.setting

import android.os.Bundle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class SettingFragmentArgs(
  public val extrasUserType: String?
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("extras_user_type", this.extrasUserType)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): SettingFragmentArgs {
      bundle.setClassLoader(SettingFragmentArgs::class.java.classLoader)
      val __extrasUserType : String?
      if (bundle.containsKey("extras_user_type")) {
        __extrasUserType = bundle.getString("extras_user_type")
      } else {
        throw IllegalArgumentException("Required argument \"extras_user_type\" is missing and does not have an android:defaultValue")
      }
      return SettingFragmentArgs(__extrasUserType)
    }
  }
}

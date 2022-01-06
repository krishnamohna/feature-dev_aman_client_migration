package com.cardio.physician.ui.views.diagnosis.step4

import android.os.Bundle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class DiagnosisFragmentStep4Args(
  public val userId: String?
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("userId", this.userId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): DiagnosisFragmentStep4Args {
      bundle.setClassLoader(DiagnosisFragmentStep4Args::class.java.classLoader)
      val __userId : String?
      if (bundle.containsKey("userId")) {
        __userId = bundle.getString("userId")
      } else {
        throw IllegalArgumentException("Required argument \"userId\" is missing and does not have an android:defaultValue")
      }
      return DiagnosisFragmentStep4Args(__userId)
    }
  }
}

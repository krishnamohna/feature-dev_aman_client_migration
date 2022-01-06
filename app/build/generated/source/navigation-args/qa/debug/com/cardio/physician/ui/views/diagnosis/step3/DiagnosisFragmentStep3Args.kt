package com.cardio.physician.ui.views.diagnosis.step3

import android.os.Bundle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class DiagnosisFragmentStep3Args(
  public val userId: String?
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("userId", this.userId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): DiagnosisFragmentStep3Args {
      bundle.setClassLoader(DiagnosisFragmentStep3Args::class.java.classLoader)
      val __userId : String?
      if (bundle.containsKey("userId")) {
        __userId = bundle.getString("userId")
      } else {
        throw IllegalArgumentException("Required argument \"userId\" is missing and does not have an android:defaultValue")
      }
      return DiagnosisFragmentStep3Args(__userId)
    }
  }
}

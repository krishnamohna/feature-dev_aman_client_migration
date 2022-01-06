package com.cardio.physician.ui.views.diagnosis.step1

import android.os.Bundle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class EditDiagnosisFragmentStep1Args(
  public val userId: String?
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("userId", this.userId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): EditDiagnosisFragmentStep1Args {
      bundle.setClassLoader(EditDiagnosisFragmentStep1Args::class.java.classLoader)
      val __userId : String?
      if (bundle.containsKey("userId")) {
        __userId = bundle.getString("userId")
      } else {
        throw IllegalArgumentException("Required argument \"userId\" is missing and does not have an android:defaultValue")
      }
      return EditDiagnosisFragmentStep1Args(__userId)
    }
  }
}

package com.cardio.physician.ui.views.auth.phone_verification

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavArgs
import com.cardio.physician.domain.login.request.PhoneVerificationDetails
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class PhoneNumberVerificationFragmentArgs(
  public val phoneVerificationDetail: PhoneVerificationDetails?,
  public val isComingFrom: Int = 1
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toBundle(): Bundle {
    val result = Bundle()
    if (Parcelable::class.java.isAssignableFrom(PhoneVerificationDetails::class.java)) {
      result.putParcelable("phoneVerificationDetail", this.phoneVerificationDetail as Parcelable?)
    } else if (Serializable::class.java.isAssignableFrom(PhoneVerificationDetails::class.java)) {
      result.putSerializable("phoneVerificationDetail", this.phoneVerificationDetail as
          Serializable?)
    } else {
      throw UnsupportedOperationException(PhoneVerificationDetails::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    result.putInt("isComingFrom", this.isComingFrom)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): PhoneNumberVerificationFragmentArgs {
      bundle.setClassLoader(PhoneNumberVerificationFragmentArgs::class.java.classLoader)
      val __phoneVerificationDetail : PhoneVerificationDetails?
      if (bundle.containsKey("phoneVerificationDetail")) {
        if (Parcelable::class.java.isAssignableFrom(PhoneVerificationDetails::class.java) ||
            Serializable::class.java.isAssignableFrom(PhoneVerificationDetails::class.java)) {
          __phoneVerificationDetail =
              bundle.get("phoneVerificationDetail") as PhoneVerificationDetails?
        } else {
          throw UnsupportedOperationException(PhoneVerificationDetails::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"phoneVerificationDetail\" is missing and does not have an android:defaultValue")
      }
      val __isComingFrom : Int
      if (bundle.containsKey("isComingFrom")) {
        __isComingFrom = bundle.getInt("isComingFrom")
      } else {
        __isComingFrom = 1
      }
      return PhoneNumberVerificationFragmentArgs(__phoneVerificationDetail, __isComingFrom)
    }
  }
}

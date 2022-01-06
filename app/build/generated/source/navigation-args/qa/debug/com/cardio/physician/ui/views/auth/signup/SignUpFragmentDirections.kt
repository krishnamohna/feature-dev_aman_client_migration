package com.cardio.physician.ui.views.auth.signup

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavDirections
import com.cardio.physician.R
import com.cardio.physician.domain.login.request.PhoneVerificationDetails
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.String
import kotlin.Suppress

public class SignUpFragmentDirections private constructor() {
  private data class SignupToPhoneVerification(
    public val phoneVerificationDetail: PhoneVerificationDetails?,
    public val isComingFrom: Int = 1
  ) : NavDirections {
    public override fun getActionId(): Int = R.id.signup_to_phone_verification

    @Suppress("CAST_NEVER_SUCCEEDS")
    public override fun getArguments(): Bundle {
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
  }

  private data class SignupToWebView(
    public val toolBarTitle: String?,
    public val webUrl: String?
  ) : NavDirections {
    public override fun getActionId(): Int = R.id.signupToWebView

    public override fun getArguments(): Bundle {
      val result = Bundle()
      result.putString("toolBarTitle", this.toolBarTitle)
      result.putString("webUrl", this.webUrl)
      return result
    }
  }

  public companion object {
    public fun signupToPhoneVerification(phoneVerificationDetail: PhoneVerificationDetails?,
        isComingFrom: Int = 1): NavDirections = SignupToPhoneVerification(phoneVerificationDetail,
        isComingFrom)

    public fun signupToWebView(toolBarTitle: String?, webUrl: String?): NavDirections =
        SignupToWebView(toolBarTitle, webUrl)
  }
}

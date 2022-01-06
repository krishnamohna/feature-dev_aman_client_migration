package com.cardio.physician.ui.views.auth.login

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.cardio.physician.R
import com.cardio.physician.domain.login.request.PhoneVerificationDetails
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class LoginFragmentDirections private constructor() {
  private data class LoginToPhoneVerification(
    public val phoneVerificationDetail: PhoneVerificationDetails?,
    public val isComingFrom: Int = 1
  ) : NavDirections {
    public override fun getActionId(): Int = R.id.loginToPhoneVerification

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

  public companion object {
    public fun loginToSignUp(): NavDirections = ActionOnlyNavDirections(R.id.loginToSignUp)

    public fun loginToPhoneVerification(phoneVerificationDetail: PhoneVerificationDetails?,
        isComingFrom: Int = 1): NavDirections = LoginToPhoneVerification(phoneVerificationDetail,
        isComingFrom)

    public fun loginToForgotPassword(): NavDirections =
        ActionOnlyNavDirections(R.id.loginToForgotPassword)
  }
}

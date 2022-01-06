package com.cardio.physician.ui.views.auth.phone_verification

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.cardio.physician.R

public class PhoneNumberVerificationFragmentDirections private constructor() {
  public companion object {
    public fun phoneVerificationToLoginScreen(): NavDirections =
        ActionOnlyNavDirections(R.id.phoneVerificationToLoginScreen)
  }
}

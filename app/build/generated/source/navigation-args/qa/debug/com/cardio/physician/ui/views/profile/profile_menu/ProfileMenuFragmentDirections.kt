package com.cardio.physician.ui.views.profile.profile_menu

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.cardio.physician.R

public class ProfileMenuFragmentDirections private constructor() {
  public companion object {
    public fun profileMenuToGetProfile(): NavDirections =
        ActionOnlyNavDirections(R.id.profileMenuToGetProfile)
  }
}

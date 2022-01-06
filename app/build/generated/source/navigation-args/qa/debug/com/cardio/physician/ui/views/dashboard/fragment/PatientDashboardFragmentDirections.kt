package com.cardio.physician.ui.views.dashboard.fragment

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.cardio.physician.R

public class PatientDashboardFragmentDirections private constructor() {
  public companion object {
    public fun dashboardToProfileFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.dashboardToProfileFragment)
  }
}

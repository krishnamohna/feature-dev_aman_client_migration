package com.cardio.physician.ui.views.dashboard.fragment

import android.os.Bundle
import androidx.navigation.NavDirections
import com.cardio.physician.R
import kotlin.Int
import kotlin.String

public class DashboardFragmentDirections private constructor() {
  private data class DashboardToDiagnosisFragment(
    public val userId: String?
  ) : NavDirections {
    public override fun getActionId(): Int = R.id.dashboardToDiagnosisFragment

    public override fun getArguments(): Bundle {
      val result = Bundle()
      result.putString("userId", this.userId)
      return result
    }
  }

  public companion object {
    public fun dashboardToDiagnosisFragment(userId: String?): NavDirections =
        DashboardToDiagnosisFragment(userId)
  }
}

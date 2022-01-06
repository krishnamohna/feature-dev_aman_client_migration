package com.cardio.physician.ui.views.diagnosis.step1

import android.os.Bundle
import androidx.navigation.NavDirections
import com.cardio.physician.R
import kotlin.Int
import kotlin.String

public class DiagnosisFragmentStep1Directions private constructor() {
  private data class ActionDiagnosisFragmentPart1ToDiagnosisFragmentPart2(
    public val userId: String?
  ) : NavDirections {
    public override fun getActionId(): Int =
        R.id.action_diagnosisFragmentPart1_to_diagnosisFragmentPart2

    public override fun getArguments(): Bundle {
      val result = Bundle()
      result.putString("userId", this.userId)
      return result
    }
  }

  public companion object {
    public fun actionDiagnosisFragmentPart1ToDiagnosisFragmentPart2(userId: String?): NavDirections
        = ActionDiagnosisFragmentPart1ToDiagnosisFragmentPart2(userId)
  }
}

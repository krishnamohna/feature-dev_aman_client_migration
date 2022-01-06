package com.cardio.physician.ui.views.diagnosis.step2

import android.os.Bundle
import androidx.navigation.NavDirections
import com.cardio.physician.R
import kotlin.Int
import kotlin.String

public class DiagnosisFragmentStep2Directions private constructor() {
  private data class ActionDiagnosisFragmentPart2ToDiagnosisFragmentPart3(
    public val userId: String?
  ) : NavDirections {
    public override fun getActionId(): Int =
        R.id.action_diagnosisFragmentPart2_to_diagnosisFragmentPart3

    public override fun getArguments(): Bundle {
      val result = Bundle()
      result.putString("userId", this.userId)
      return result
    }
  }

  public companion object {
    public fun actionDiagnosisFragmentPart2ToDiagnosisFragmentPart3(userId: String?): NavDirections
        = ActionDiagnosisFragmentPart2ToDiagnosisFragmentPart3(userId)
  }
}

package com.cardio.physician.ui.views.diagnosis.step3

import android.os.Bundle
import androidx.navigation.NavDirections
import com.cardio.physician.R
import kotlin.Int
import kotlin.String

public class DiagnosisFragmentStep3Directions private constructor() {
  private data class ActionDiagnosisFragmentPart3ToDiagnosisFragmentPart4(
    public val userId: String?
  ) : NavDirections {
    public override fun getActionId(): Int =
        R.id.action_diagnosisFragmentPart3_to_diagnosisFragmentPart4

    public override fun getArguments(): Bundle {
      val result = Bundle()
      result.putString("userId", this.userId)
      return result
    }
  }

  public companion object {
    public fun actionDiagnosisFragmentPart3ToDiagnosisFragmentPart4(userId: String?): NavDirections
        = ActionDiagnosisFragmentPart3ToDiagnosisFragmentPart4(userId)
  }
}

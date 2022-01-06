package com.cardio.physician.ui.views.profile.setting

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.cardio.physician.R
import kotlin.Int
import kotlin.String

public class SettingFragmentDirections private constructor() {
  private data class SettingToWebView(
    public val toolBarTitle: String?,
    public val webUrl: String?
  ) : NavDirections {
    public override fun getActionId(): Int = R.id.settingToWebView

    public override fun getArguments(): Bundle {
      val result = Bundle()
      result.putString("toolBarTitle", this.toolBarTitle)
      result.putString("webUrl", this.webUrl)
      return result
    }
  }

  public companion object {
    public fun settingToChangePasswordFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.settingToChangePasswordFragment)

    public fun settingToWebView(toolBarTitle: String?, webUrl: String?): NavDirections =
        SettingToWebView(toolBarTitle, webUrl)
  }
}

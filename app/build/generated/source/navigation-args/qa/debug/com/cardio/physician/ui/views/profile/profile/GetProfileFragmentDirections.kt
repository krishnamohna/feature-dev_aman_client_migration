package com.cardio.physician.ui.views.profile.profile

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.cardio.physician.R
import com.cardio.physician.domain.common.model.UserModel
import java.io.Serializable
import kotlin.Int
import kotlin.String
import kotlin.Suppress

public class GetProfileFragmentDirections private constructor() {
  private data class GetProfileToEditProfile(
    public val extrasUserModel: UserModel? = null
  ) : NavDirections {
    public override fun getActionId(): Int = R.id.getProfileToEditProfile

    @Suppress("CAST_NEVER_SUCCEEDS")
    public override fun getArguments(): Bundle {
      val result = Bundle()
      if (Parcelable::class.java.isAssignableFrom(UserModel::class.java)) {
        result.putParcelable("extras_user_model", this.extrasUserModel as Parcelable?)
      } else if (Serializable::class.java.isAssignableFrom(UserModel::class.java)) {
        result.putSerializable("extras_user_model", this.extrasUserModel as Serializable?)
      }
      return result
    }
  }

  private data class GetProfileToSettingFragment(
    public val extrasUserType: String?
  ) : NavDirections {
    public override fun getActionId(): Int = R.id.getProfileToSettingFragment

    public override fun getArguments(): Bundle {
      val result = Bundle()
      result.putString("extras_user_type", this.extrasUserType)
      return result
    }
  }

  public companion object {
    public fun getProfileToEditProfile(extrasUserModel: UserModel? = null): NavDirections =
        GetProfileToEditProfile(extrasUserModel)

    public fun getProfileToSyncHealthData(): NavDirections =
        ActionOnlyNavDirections(R.id.getProfileToSyncHealthData)

    public fun getProfileToSettingFragment(extrasUserType: String?): NavDirections =
        GetProfileToSettingFragment(extrasUserType)
  }
}

package com.cardio.physician.ui.views.profile.editprofile

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavArgs
import com.cardio.physician.domain.common.model.UserModel
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class EditProfileFragmentArgs(
  public val extrasUserModel: UserModel? = null
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toBundle(): Bundle {
    val result = Bundle()
    if (Parcelable::class.java.isAssignableFrom(UserModel::class.java)) {
      result.putParcelable("extras_user_model", this.extrasUserModel as Parcelable?)
    } else if (Serializable::class.java.isAssignableFrom(UserModel::class.java)) {
      result.putSerializable("extras_user_model", this.extrasUserModel as Serializable?)
    }
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): EditProfileFragmentArgs {
      bundle.setClassLoader(EditProfileFragmentArgs::class.java.classLoader)
      val __extrasUserModel : UserModel?
      if (bundle.containsKey("extras_user_model")) {
        if (Parcelable::class.java.isAssignableFrom(UserModel::class.java) ||
            Serializable::class.java.isAssignableFrom(UserModel::class.java)) {
          __extrasUserModel = bundle.get("extras_user_model") as UserModel?
        } else {
          throw UnsupportedOperationException(UserModel::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
      } else {
        __extrasUserModel = null
      }
      return EditProfileFragmentArgs(__extrasUserModel)
    }
  }
}

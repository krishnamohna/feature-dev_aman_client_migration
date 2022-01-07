// Generated by view binder compiler. Do not edit!
package com.cardio.physician.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.cardio.physician.R;
import java.lang.NullPointerException;
import java.lang.Override;

public final class SettingsStyleBinding implements ViewBinding {
  @NonNull
  private final ImageView rootView;

  @NonNull
  public final ImageView settingsButton;

  private SettingsStyleBinding(@NonNull ImageView rootView, @NonNull ImageView settingsButton) {
    this.rootView = rootView;
    this.settingsButton = settingsButton;
  }

  @Override
  @NonNull
  public ImageView getRoot() {
    return rootView;
  }

  @NonNull
  public static SettingsStyleBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static SettingsStyleBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.settings_style, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static SettingsStyleBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    ImageView settingsButton = (ImageView) rootView;

    return new SettingsStyleBinding((ImageView) rootView, settingsButton);
  }
}
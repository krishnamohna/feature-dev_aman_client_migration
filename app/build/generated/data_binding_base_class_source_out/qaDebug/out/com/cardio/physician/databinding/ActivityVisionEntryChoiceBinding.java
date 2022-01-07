// Generated by view binder compiler. Do not edit!
package com.cardio.physician.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.cardio.physician.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityVisionEntryChoiceBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView javaEntryPoint;

  @NonNull
  public final TextView kotlinEntryPoint;

  private ActivityVisionEntryChoiceBinding(@NonNull LinearLayout rootView,
      @NonNull TextView javaEntryPoint, @NonNull TextView kotlinEntryPoint) {
    this.rootView = rootView;
    this.javaEntryPoint = javaEntryPoint;
    this.kotlinEntryPoint = kotlinEntryPoint;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityVisionEntryChoiceBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityVisionEntryChoiceBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_vision_entry_choice, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityVisionEntryChoiceBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.java_entry_point;
      TextView javaEntryPoint = rootView.findViewById(id);
      if (javaEntryPoint == null) {
        break missingId;
      }

      id = R.id.kotlin_entry_point;
      TextView kotlinEntryPoint = rootView.findViewById(id);
      if (kotlinEntryPoint == null) {
        break missingId;
      }

      return new ActivityVisionEntryChoiceBinding((LinearLayout) rootView, javaEntryPoint,
          kotlinEntryPoint);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
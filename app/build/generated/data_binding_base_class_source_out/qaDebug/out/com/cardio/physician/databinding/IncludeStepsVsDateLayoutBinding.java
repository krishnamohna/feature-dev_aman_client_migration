// Generated by view binder compiler. Do not edit!
package com.cardio.physician.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.cardio.physician.R;
import com.google.android.material.card.MaterialCardView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class IncludeStepsVsDateLayoutBinding implements ViewBinding {
  @NonNull
  private final MaterialCardView rootView;

  @NonNull
  public final ConstraintLayout clStepCount;

  @NonNull
  public final TextView tvAvgValue;

  @NonNull
  public final TextView tvGraphLabel;

  @NonNull
  public final TextView tvHrLabel;

  @NonNull
  public final TextView tvXaxisTitle;

  @NonNull
  public final TextView tvYaxisTitle;

  private IncludeStepsVsDateLayoutBinding(@NonNull MaterialCardView rootView,
      @NonNull ConstraintLayout clStepCount, @NonNull TextView tvAvgValue,
      @NonNull TextView tvGraphLabel, @NonNull TextView tvHrLabel, @NonNull TextView tvXaxisTitle,
      @NonNull TextView tvYaxisTitle) {
    this.rootView = rootView;
    this.clStepCount = clStepCount;
    this.tvAvgValue = tvAvgValue;
    this.tvGraphLabel = tvGraphLabel;
    this.tvHrLabel = tvHrLabel;
    this.tvXaxisTitle = tvXaxisTitle;
    this.tvYaxisTitle = tvYaxisTitle;
  }

  @Override
  @NonNull
  public MaterialCardView getRoot() {
    return rootView;
  }

  @NonNull
  public static IncludeStepsVsDateLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static IncludeStepsVsDateLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.include_steps_vs_date_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static IncludeStepsVsDateLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.clStepCount;
      ConstraintLayout clStepCount = rootView.findViewById(id);
      if (clStepCount == null) {
        break missingId;
      }

      id = R.id.tvAvgValue;
      TextView tvAvgValue = rootView.findViewById(id);
      if (tvAvgValue == null) {
        break missingId;
      }

      id = R.id.tvGraphLabel;
      TextView tvGraphLabel = rootView.findViewById(id);
      if (tvGraphLabel == null) {
        break missingId;
      }

      id = R.id.tvHrLabel;
      TextView tvHrLabel = rootView.findViewById(id);
      if (tvHrLabel == null) {
        break missingId;
      }

      id = R.id.tvXaxisTitle;
      TextView tvXaxisTitle = rootView.findViewById(id);
      if (tvXaxisTitle == null) {
        break missingId;
      }

      id = R.id.tvYaxisTitle;
      TextView tvYaxisTitle = rootView.findViewById(id);
      if (tvYaxisTitle == null) {
        break missingId;
      }

      return new IncludeStepsVsDateLayoutBinding((MaterialCardView) rootView, clStepCount,
          tvAvgValue, tvGraphLabel, tvHrLabel, tvXaxisTitle, tvYaxisTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

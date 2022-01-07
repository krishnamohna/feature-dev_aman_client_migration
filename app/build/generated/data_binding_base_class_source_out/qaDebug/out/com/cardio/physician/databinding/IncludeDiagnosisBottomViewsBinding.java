// Generated by view binder compiler. Do not edit!
package com.cardio.physician.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.cardio.physician.R;
import com.cardio.physician.ui.common.customviews.AppButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class IncludeDiagnosisBottomViewsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppButton btCancel;

  @NonNull
  public final AppButton btNext;

  @NonNull
  public final ConstraintLayout cvDiagnosisBottomContainer;

  private IncludeDiagnosisBottomViewsBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppButton btCancel, @NonNull AppButton btNext,
      @NonNull ConstraintLayout cvDiagnosisBottomContainer) {
    this.rootView = rootView;
    this.btCancel = btCancel;
    this.btNext = btNext;
    this.cvDiagnosisBottomContainer = cvDiagnosisBottomContainer;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static IncludeDiagnosisBottomViewsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static IncludeDiagnosisBottomViewsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.include_diagnosis_bottom_views, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static IncludeDiagnosisBottomViewsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btCancel;
      AppButton btCancel = rootView.findViewById(id);
      if (btCancel == null) {
        break missingId;
      }

      id = R.id.btNext;
      AppButton btNext = rootView.findViewById(id);
      if (btNext == null) {
        break missingId;
      }

      ConstraintLayout cvDiagnosisBottomContainer = (ConstraintLayout) rootView;

      return new IncludeDiagnosisBottomViewsBinding((ConstraintLayout) rootView, btCancel, btNext,
          cvDiagnosisBottomContainer);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
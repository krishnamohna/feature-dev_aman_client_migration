// Generated by view binder compiler. Do not edit!
package com.cardio.physician.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.cardio.physician.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class BottomSheetPhysicianOperationBinding implements ViewBinding {
  @NonNull
  private final MaterialCardView rootView;

  @NonNull
  public final MaterialTextView tvAddDataLabel;

  @NonNull
  public final MaterialTextView tvCancelLabel;

  @NonNull
  public final MaterialTextView tvIllnessLabel;

  @NonNull
  public final MaterialTextView tvPatientLabel;

  @NonNull
  public final View view;

  @NonNull
  public final View view1;

  private BottomSheetPhysicianOperationBinding(@NonNull MaterialCardView rootView,
      @NonNull MaterialTextView tvAddDataLabel, @NonNull MaterialTextView tvCancelLabel,
      @NonNull MaterialTextView tvIllnessLabel, @NonNull MaterialTextView tvPatientLabel,
      @NonNull View view, @NonNull View view1) {
    this.rootView = rootView;
    this.tvAddDataLabel = tvAddDataLabel;
    this.tvCancelLabel = tvCancelLabel;
    this.tvIllnessLabel = tvIllnessLabel;
    this.tvPatientLabel = tvPatientLabel;
    this.view = view;
    this.view1 = view1;
  }

  @Override
  @NonNull
  public MaterialCardView getRoot() {
    return rootView;
  }

  @NonNull
  public static BottomSheetPhysicianOperationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static BottomSheetPhysicianOperationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.bottom_sheet_physician_operation, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static BottomSheetPhysicianOperationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.tvAddDataLabel;
      MaterialTextView tvAddDataLabel = rootView.findViewById(id);
      if (tvAddDataLabel == null) {
        break missingId;
      }

      id = R.id.tvCancelLabel;
      MaterialTextView tvCancelLabel = rootView.findViewById(id);
      if (tvCancelLabel == null) {
        break missingId;
      }

      id = R.id.tvIllnessLabel;
      MaterialTextView tvIllnessLabel = rootView.findViewById(id);
      if (tvIllnessLabel == null) {
        break missingId;
      }

      id = R.id.tvPatientLabel;
      MaterialTextView tvPatientLabel = rootView.findViewById(id);
      if (tvPatientLabel == null) {
        break missingId;
      }

      id = R.id.view;
      View view = rootView.findViewById(id);
      if (view == null) {
        break missingId;
      }

      id = R.id.view_1;
      View view1 = rootView.findViewById(id);
      if (view1 == null) {
        break missingId;
      }

      return new BottomSheetPhysicianOperationBinding((MaterialCardView) rootView, tvAddDataLabel,
          tvCancelLabel, tvIllnessLabel, tvPatientLabel, view, view1);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
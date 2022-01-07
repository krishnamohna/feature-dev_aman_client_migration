// Generated by view binder compiler. Do not edit!
package com.cardio.physician.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.cardio.physician.R;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.card.MaterialCardView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class IncludeChbMedicationDashboardLayoutBinding implements ViewBinding {
  @NonNull
  private final MaterialCardView rootView;

  @NonNull
  public final MaterialCardView cvChfMed;

  @NonNull
  public final FlexboxLayout flAce;

  @NonNull
  public final FlexboxLayout flArni;

  @NonNull
  public final FlexboxLayout flBetaBlocker;

  @NonNull
  public final FlexboxLayout flMra;

  @NonNull
  public final FlexboxLayout flOther;

  @NonNull
  public final FlexboxLayout flSgl;

  @NonNull
  public final ImageView ivEditChbMed;

  @NonNull
  public final TextView tvAceLabel;

  @NonNull
  public final TextView tvArniLabel;

  @NonNull
  public final TextView tvBetaBlockerLabel;

  @NonNull
  public final TextView tvChfMedLabel;

  @NonNull
  public final TextView tvMraLabel;

  @NonNull
  public final TextView tvOtherLabel;

  @NonNull
  public final TextView tvSglLabel;

  private IncludeChbMedicationDashboardLayoutBinding(@NonNull MaterialCardView rootView,
      @NonNull MaterialCardView cvChfMed, @NonNull FlexboxLayout flAce,
      @NonNull FlexboxLayout flArni, @NonNull FlexboxLayout flBetaBlocker,
      @NonNull FlexboxLayout flMra, @NonNull FlexboxLayout flOther, @NonNull FlexboxLayout flSgl,
      @NonNull ImageView ivEditChbMed, @NonNull TextView tvAceLabel, @NonNull TextView tvArniLabel,
      @NonNull TextView tvBetaBlockerLabel, @NonNull TextView tvChfMedLabel,
      @NonNull TextView tvMraLabel, @NonNull TextView tvOtherLabel, @NonNull TextView tvSglLabel) {
    this.rootView = rootView;
    this.cvChfMed = cvChfMed;
    this.flAce = flAce;
    this.flArni = flArni;
    this.flBetaBlocker = flBetaBlocker;
    this.flMra = flMra;
    this.flOther = flOther;
    this.flSgl = flSgl;
    this.ivEditChbMed = ivEditChbMed;
    this.tvAceLabel = tvAceLabel;
    this.tvArniLabel = tvArniLabel;
    this.tvBetaBlockerLabel = tvBetaBlockerLabel;
    this.tvChfMedLabel = tvChfMedLabel;
    this.tvMraLabel = tvMraLabel;
    this.tvOtherLabel = tvOtherLabel;
    this.tvSglLabel = tvSglLabel;
  }

  @Override
  @NonNull
  public MaterialCardView getRoot() {
    return rootView;
  }

  @NonNull
  public static IncludeChbMedicationDashboardLayoutBinding inflate(
      @NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static IncludeChbMedicationDashboardLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.include_chb_medication_dashboard_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static IncludeChbMedicationDashboardLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      MaterialCardView cvChfMed = (MaterialCardView) rootView;

      id = R.id.flAce;
      FlexboxLayout flAce = rootView.findViewById(id);
      if (flAce == null) {
        break missingId;
      }

      id = R.id.flArni;
      FlexboxLayout flArni = rootView.findViewById(id);
      if (flArni == null) {
        break missingId;
      }

      id = R.id.flBetaBlocker;
      FlexboxLayout flBetaBlocker = rootView.findViewById(id);
      if (flBetaBlocker == null) {
        break missingId;
      }

      id = R.id.flMra;
      FlexboxLayout flMra = rootView.findViewById(id);
      if (flMra == null) {
        break missingId;
      }

      id = R.id.flOther;
      FlexboxLayout flOther = rootView.findViewById(id);
      if (flOther == null) {
        break missingId;
      }

      id = R.id.flSgl;
      FlexboxLayout flSgl = rootView.findViewById(id);
      if (flSgl == null) {
        break missingId;
      }

      id = R.id.ivEditChbMed;
      ImageView ivEditChbMed = rootView.findViewById(id);
      if (ivEditChbMed == null) {
        break missingId;
      }

      id = R.id.tvAceLabel;
      TextView tvAceLabel = rootView.findViewById(id);
      if (tvAceLabel == null) {
        break missingId;
      }

      id = R.id.tvArniLabel;
      TextView tvArniLabel = rootView.findViewById(id);
      if (tvArniLabel == null) {
        break missingId;
      }

      id = R.id.tvBetaBlockerLabel;
      TextView tvBetaBlockerLabel = rootView.findViewById(id);
      if (tvBetaBlockerLabel == null) {
        break missingId;
      }

      id = R.id.tvChfMedLabel;
      TextView tvChfMedLabel = rootView.findViewById(id);
      if (tvChfMedLabel == null) {
        break missingId;
      }

      id = R.id.tvMraLabel;
      TextView tvMraLabel = rootView.findViewById(id);
      if (tvMraLabel == null) {
        break missingId;
      }

      id = R.id.tvOtherLabel;
      TextView tvOtherLabel = rootView.findViewById(id);
      if (tvOtherLabel == null) {
        break missingId;
      }

      id = R.id.tvSglLabel;
      TextView tvSglLabel = rootView.findViewById(id);
      if (tvSglLabel == null) {
        break missingId;
      }

      return new IncludeChbMedicationDashboardLayoutBinding((MaterialCardView) rootView, cvChfMed,
          flAce, flArni, flBetaBlocker, flMra, flOther, flSgl, ivEditChbMed, tvAceLabel,
          tvArniLabel, tvBetaBlockerLabel, tvChfMedLabel, tvMraLabel, tvOtherLabel, tvSglLabel);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
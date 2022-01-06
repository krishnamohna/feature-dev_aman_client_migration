// Generated by view binder compiler. Do not edit!
package com.cardio.physician.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.cardio.physician.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class IncludeHealthDetailBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Barrier barrierBpLabels;

  @NonNull
  public final Barrier barrierNames;

  @NonNull
  public final Barrier barrierWeightLabels;

  @NonNull
  public final ConstraintLayout clHealthDetail;

  @NonNull
  public final AppCompatEditText edtBottomBp;

  @NonNull
  public final AppCompatEditText edtHeartRate;

  @NonNull
  public final AppCompatEditText edtStepCount;

  @NonNull
  public final AppCompatEditText edtTopBp;

  @NonNull
  public final AppCompatEditText edtWeight;

  @NonNull
  public final AppCompatTextView textStepCount;

  @NonNull
  public final AppCompatTextView tvBottomBpErrror;

  @NonNull
  public final AppCompatTextView tvErrorHeartRate;

  @NonNull
  public final AppCompatTextView tvStepCountError;

  @NonNull
  public final AppCompatTextView tvTopBpErrro;

  @NonNull
  public final AppCompatTextView tvWeight;

  @NonNull
  public final AppCompatTextView tvWeightError;

  @NonNull
  public final AppCompatTextView txtBottomBp;

  @NonNull
  public final AppCompatTextView txtHeartRate;

  @NonNull
  public final AppCompatTextView txtTopBp;

  private IncludeHealthDetailBinding(@NonNull ConstraintLayout rootView,
      @NonNull Barrier barrierBpLabels, @NonNull Barrier barrierNames,
      @NonNull Barrier barrierWeightLabels, @NonNull ConstraintLayout clHealthDetail,
      @NonNull AppCompatEditText edtBottomBp, @NonNull AppCompatEditText edtHeartRate,
      @NonNull AppCompatEditText edtStepCount, @NonNull AppCompatEditText edtTopBp,
      @NonNull AppCompatEditText edtWeight, @NonNull AppCompatTextView textStepCount,
      @NonNull AppCompatTextView tvBottomBpErrror, @NonNull AppCompatTextView tvErrorHeartRate,
      @NonNull AppCompatTextView tvStepCountError, @NonNull AppCompatTextView tvTopBpErrro,
      @NonNull AppCompatTextView tvWeight, @NonNull AppCompatTextView tvWeightError,
      @NonNull AppCompatTextView txtBottomBp, @NonNull AppCompatTextView txtHeartRate,
      @NonNull AppCompatTextView txtTopBp) {
    this.rootView = rootView;
    this.barrierBpLabels = barrierBpLabels;
    this.barrierNames = barrierNames;
    this.barrierWeightLabels = barrierWeightLabels;
    this.clHealthDetail = clHealthDetail;
    this.edtBottomBp = edtBottomBp;
    this.edtHeartRate = edtHeartRate;
    this.edtStepCount = edtStepCount;
    this.edtTopBp = edtTopBp;
    this.edtWeight = edtWeight;
    this.textStepCount = textStepCount;
    this.tvBottomBpErrror = tvBottomBpErrror;
    this.tvErrorHeartRate = tvErrorHeartRate;
    this.tvStepCountError = tvStepCountError;
    this.tvTopBpErrro = tvTopBpErrro;
    this.tvWeight = tvWeight;
    this.tvWeightError = tvWeightError;
    this.txtBottomBp = txtBottomBp;
    this.txtHeartRate = txtHeartRate;
    this.txtTopBp = txtTopBp;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static IncludeHealthDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static IncludeHealthDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.include_health_detail, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static IncludeHealthDetailBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.barrierBpLabels;
      Barrier barrierBpLabels = rootView.findViewById(id);
      if (barrierBpLabels == null) {
        break missingId;
      }

      id = R.id.barrierNames;
      Barrier barrierNames = rootView.findViewById(id);
      if (barrierNames == null) {
        break missingId;
      }

      id = R.id.barrierWeightLabels;
      Barrier barrierWeightLabels = rootView.findViewById(id);
      if (barrierWeightLabels == null) {
        break missingId;
      }

      ConstraintLayout clHealthDetail = (ConstraintLayout) rootView;

      id = R.id.edtBottomBp;
      AppCompatEditText edtBottomBp = rootView.findViewById(id);
      if (edtBottomBp == null) {
        break missingId;
      }

      id = R.id.edtHeartRate;
      AppCompatEditText edtHeartRate = rootView.findViewById(id);
      if (edtHeartRate == null) {
        break missingId;
      }

      id = R.id.edtStepCount;
      AppCompatEditText edtStepCount = rootView.findViewById(id);
      if (edtStepCount == null) {
        break missingId;
      }

      id = R.id.edtTopBp;
      AppCompatEditText edtTopBp = rootView.findViewById(id);
      if (edtTopBp == null) {
        break missingId;
      }

      id = R.id.edtWeight;
      AppCompatEditText edtWeight = rootView.findViewById(id);
      if (edtWeight == null) {
        break missingId;
      }

      id = R.id.textStepCount;
      AppCompatTextView textStepCount = rootView.findViewById(id);
      if (textStepCount == null) {
        break missingId;
      }

      id = R.id.tvBottomBpErrror;
      AppCompatTextView tvBottomBpErrror = rootView.findViewById(id);
      if (tvBottomBpErrror == null) {
        break missingId;
      }

      id = R.id.tvErrorHeartRate;
      AppCompatTextView tvErrorHeartRate = rootView.findViewById(id);
      if (tvErrorHeartRate == null) {
        break missingId;
      }

      id = R.id.tvStepCountError;
      AppCompatTextView tvStepCountError = rootView.findViewById(id);
      if (tvStepCountError == null) {
        break missingId;
      }

      id = R.id.tvTopBpErrro;
      AppCompatTextView tvTopBpErrro = rootView.findViewById(id);
      if (tvTopBpErrro == null) {
        break missingId;
      }

      id = R.id.tvWeight;
      AppCompatTextView tvWeight = rootView.findViewById(id);
      if (tvWeight == null) {
        break missingId;
      }

      id = R.id.tvWeightError;
      AppCompatTextView tvWeightError = rootView.findViewById(id);
      if (tvWeightError == null) {
        break missingId;
      }

      id = R.id.txtBottomBp;
      AppCompatTextView txtBottomBp = rootView.findViewById(id);
      if (txtBottomBp == null) {
        break missingId;
      }

      id = R.id.txtHeartRate;
      AppCompatTextView txtHeartRate = rootView.findViewById(id);
      if (txtHeartRate == null) {
        break missingId;
      }

      id = R.id.txtTopBp;
      AppCompatTextView txtTopBp = rootView.findViewById(id);
      if (txtTopBp == null) {
        break missingId;
      }

      return new IncludeHealthDetailBinding((ConstraintLayout) rootView, barrierBpLabels,
          barrierNames, barrierWeightLabels, clHealthDetail, edtBottomBp, edtHeartRate,
          edtStepCount, edtTopBp, edtWeight, textStepCount, tvBottomBpErrror, tvErrorHeartRate,
          tvStepCountError, tvTopBpErrro, tvWeight, tvWeightError, txtBottomBp, txtHeartRate,
          txtTopBp);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

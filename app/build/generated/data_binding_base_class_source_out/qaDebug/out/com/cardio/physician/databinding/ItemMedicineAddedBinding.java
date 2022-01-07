// Generated by view binder compiler. Do not edit!
package com.cardio.physician.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import com.cardio.physician.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemMedicineAddedBinding implements ViewBinding {
  @NonNull
  private final LinearLayoutCompat rootView;

  @NonNull
  public final ImageView ivRemoveMed;

  @NonNull
  public final TextView tvMed;

  private ItemMedicineAddedBinding(@NonNull LinearLayoutCompat rootView,
      @NonNull ImageView ivRemoveMed, @NonNull TextView tvMed) {
    this.rootView = rootView;
    this.ivRemoveMed = ivRemoveMed;
    this.tvMed = tvMed;
  }

  @Override
  @NonNull
  public LinearLayoutCompat getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemMedicineAddedBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemMedicineAddedBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_medicine_added, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemMedicineAddedBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.ivRemoveMed;
      ImageView ivRemoveMed = rootView.findViewById(id);
      if (ivRemoveMed == null) {
        break missingId;
      }

      id = R.id.tvMed;
      TextView tvMed = rootView.findViewById(id);
      if (tvMed == null) {
        break missingId;
      }

      return new ItemMedicineAddedBinding((LinearLayoutCompat) rootView, ivRemoveMed, tvMed);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
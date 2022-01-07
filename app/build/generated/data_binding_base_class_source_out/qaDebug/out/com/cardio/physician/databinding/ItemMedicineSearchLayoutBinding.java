// Generated by view binder compiler. Do not edit!
package com.cardio.physician.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.cardio.physician.R;
import com.google.android.material.textview.MaterialTextView;
import java.lang.NullPointerException;
import java.lang.Override;

public final class ItemMedicineSearchLayoutBinding implements ViewBinding {
  @NonNull
  private final MaterialTextView rootView;

  @NonNull
  public final MaterialTextView tvSearchedMed;

  private ItemMedicineSearchLayoutBinding(@NonNull MaterialTextView rootView,
      @NonNull MaterialTextView tvSearchedMed) {
    this.rootView = rootView;
    this.tvSearchedMed = tvSearchedMed;
  }

  @Override
  @NonNull
  public MaterialTextView getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemMedicineSearchLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemMedicineSearchLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_medicine_search_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemMedicineSearchLayoutBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    MaterialTextView tvSearchedMed = (MaterialTextView) rootView;

    return new ItemMedicineSearchLayoutBinding((MaterialTextView) rootView, tvSearchedMed);
  }
}
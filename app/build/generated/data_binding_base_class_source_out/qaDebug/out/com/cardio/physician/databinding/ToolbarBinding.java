// Generated by view binder compiler. Do not edit!
package com.cardio.physician.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.cardio.physician.R;
import com.google.android.material.imageview.ShapeableImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ToolbarBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatImageView backBtn;

  @NonNull
  public final AppCompatButton buttonConnect;

  @NonNull
  public final AppCompatImageView imgEditProfile;

  @NonNull
  public final ShapeableImageView imgProfilePicToolbar;

  @NonNull
  public final ConstraintLayout toolBarContainer;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final AppCompatTextView toolbarTitle;

  private ToolbarBinding(@NonNull ConstraintLayout rootView, @NonNull AppCompatImageView backBtn,
      @NonNull AppCompatButton buttonConnect, @NonNull AppCompatImageView imgEditProfile,
      @NonNull ShapeableImageView imgProfilePicToolbar, @NonNull ConstraintLayout toolBarContainer,
      @NonNull Toolbar toolbar, @NonNull AppCompatTextView toolbarTitle) {
    this.rootView = rootView;
    this.backBtn = backBtn;
    this.buttonConnect = buttonConnect;
    this.imgEditProfile = imgEditProfile;
    this.imgProfilePicToolbar = imgProfilePicToolbar;
    this.toolBarContainer = toolBarContainer;
    this.toolbar = toolbar;
    this.toolbarTitle = toolbarTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ToolbarBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ToolbarBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.toolbar, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ToolbarBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backBtn;
      AppCompatImageView backBtn = rootView.findViewById(id);
      if (backBtn == null) {
        break missingId;
      }

      id = R.id.buttonConnect;
      AppCompatButton buttonConnect = rootView.findViewById(id);
      if (buttonConnect == null) {
        break missingId;
      }

      id = R.id.imgEditProfile;
      AppCompatImageView imgEditProfile = rootView.findViewById(id);
      if (imgEditProfile == null) {
        break missingId;
      }

      id = R.id.imgProfilePicToolbar;
      ShapeableImageView imgProfilePicToolbar = rootView.findViewById(id);
      if (imgProfilePicToolbar == null) {
        break missingId;
      }

      ConstraintLayout toolBarContainer = (ConstraintLayout) rootView;

      id = R.id.toolbar;
      Toolbar toolbar = rootView.findViewById(id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.toolbarTitle;
      AppCompatTextView toolbarTitle = rootView.findViewById(id);
      if (toolbarTitle == null) {
        break missingId;
      }

      return new ToolbarBinding((ConstraintLayout) rootView, backBtn, buttonConnect, imgEditProfile,
          imgProfilePicToolbar, toolBarContainer, toolbar, toolbarTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
// Generated by view binder compiler. Do not edit!
package com.cardio.physician.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import com.cardio.physician.R;
import com.google.android.material.card.MaterialCardView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDashboardBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatImageView btnDashboardOne;

  @NonNull
  public final AppCompatImageView btnDashboardTwo;

  @NonNull
  public final AppCompatImageView btnProfileMenu;

  @NonNull
  public final View btnTempDashboardTwo;

  @NonNull
  public final MaterialCardView cVBottomBar;

  @NonNull
  public final ConstraintLayout constraintLayout;

  @NonNull
  public final FragmentContainerView navHostFragment;

  private ActivityDashboardBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatImageView btnDashboardOne, @NonNull AppCompatImageView btnDashboardTwo,
      @NonNull AppCompatImageView btnProfileMenu, @NonNull View btnTempDashboardTwo,
      @NonNull MaterialCardView cVBottomBar, @NonNull ConstraintLayout constraintLayout,
      @NonNull FragmentContainerView navHostFragment) {
    this.rootView = rootView;
    this.btnDashboardOne = btnDashboardOne;
    this.btnDashboardTwo = btnDashboardTwo;
    this.btnProfileMenu = btnProfileMenu;
    this.btnTempDashboardTwo = btnTempDashboardTwo;
    this.cVBottomBar = cVBottomBar;
    this.constraintLayout = constraintLayout;
    this.navHostFragment = navHostFragment;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDashboardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDashboardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_dashboard, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDashboardBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnDashboardOne;
      AppCompatImageView btnDashboardOne = rootView.findViewById(id);
      if (btnDashboardOne == null) {
        break missingId;
      }

      id = R.id.btnDashboardTwo;
      AppCompatImageView btnDashboardTwo = rootView.findViewById(id);
      if (btnDashboardTwo == null) {
        break missingId;
      }

      id = R.id.btnProfileMenu;
      AppCompatImageView btnProfileMenu = rootView.findViewById(id);
      if (btnProfileMenu == null) {
        break missingId;
      }

      id = R.id.btnTempDashboardTwo;
      View btnTempDashboardTwo = rootView.findViewById(id);
      if (btnTempDashboardTwo == null) {
        break missingId;
      }

      id = R.id.cVBottomBar;
      MaterialCardView cVBottomBar = rootView.findViewById(id);
      if (cVBottomBar == null) {
        break missingId;
      }

      id = R.id.constraintLayout;
      ConstraintLayout constraintLayout = rootView.findViewById(id);
      if (constraintLayout == null) {
        break missingId;
      }

      id = R.id.navHostFragment;
      FragmentContainerView navHostFragment = rootView.findViewById(id);
      if (navHostFragment == null) {
        break missingId;
      }

      return new ActivityDashboardBinding((ConstraintLayout) rootView, btnDashboardOne,
          btnDashboardTwo, btnProfileMenu, btnTempDashboardTwo, cVBottomBar, constraintLayout,
          navHostFragment);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

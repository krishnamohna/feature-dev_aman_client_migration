// Generated by view binder compiler. Do not edit!
package com.cardio.physician.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.cardio.physician.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityNotificationsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ToolbarBinding headerView;

  @NonNull
  public final RecyclerView rvNotifications;

  @NonNull
  public final AppCompatImageView tvNoNotificationFound;

  private ActivityNotificationsBinding(@NonNull ConstraintLayout rootView,
      @NonNull ToolbarBinding headerView, @NonNull RecyclerView rvNotifications,
      @NonNull AppCompatImageView tvNoNotificationFound) {
    this.rootView = rootView;
    this.headerView = headerView;
    this.rvNotifications = rvNotifications;
    this.tvNoNotificationFound = tvNoNotificationFound;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityNotificationsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityNotificationsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_notifications, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityNotificationsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.headerView;
      View headerView = rootView.findViewById(id);
      if (headerView == null) {
        break missingId;
      }
      ToolbarBinding binding_headerView = ToolbarBinding.bind(headerView);

      id = R.id.rvNotifications;
      RecyclerView rvNotifications = rootView.findViewById(id);
      if (rvNotifications == null) {
        break missingId;
      }

      id = R.id.tvNoNotificationFound;
      AppCompatImageView tvNoNotificationFound = rootView.findViewById(id);
      if (tvNoNotificationFound == null) {
        break missingId;
      }

      return new ActivityNotificationsBinding((ConstraintLayout) rootView, binding_headerView,
          rvNotifications, tvNoNotificationFound);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
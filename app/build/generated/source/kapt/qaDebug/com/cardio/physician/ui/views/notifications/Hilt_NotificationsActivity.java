package com.cardio.physician.ui.views.notifications;

import android.content.Context;
import androidx.activity.contextaware.OnContextAvailableListener;
import com.cardio.physician.ui.common.base.activity.BaseToolbarActivity;
import dagger.hilt.internal.GeneratedComponentManagerHolder;
import dagger.hilt.internal.UnsafeCasts;
import java.lang.Override;

/**
 * A generated base class to be extended by the @dagger.hilt.android.AndroidEntryPoint annotated class. If using the Gradle plugin, this is swapped as the base class via bytecode transformation.
 */
public abstract class Hilt_NotificationsActivity extends BaseToolbarActivity {
  private boolean injected = false;

  Hilt_NotificationsActivity() {
    super();
    _initHiltInternal();
  }

  private void _initHiltInternal() {
    addOnContextAvailableListener(new OnContextAvailableListener() {
      @Override
      public void onContextAvailable(Context context) {
        inject();
      }
    });
  }

  protected void inject() {
    if (!injected) {
      injected = true;
      ((NotificationsActivity_GeneratedInjector) UnsafeCasts.<GeneratedComponentManagerHolder>unsafeCast(this).generatedComponent()).injectNotificationsActivity(UnsafeCasts.<NotificationsActivity>unsafeCast(this));
    }
  }
}

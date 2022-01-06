package com.cardio.physician.ui.views.tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import com.cardio.physician.R;
import com.cardio.physician.ui.common.base.activity.BaseActivity;
import com.cardio.physician.databinding.ActivityTutorialBinding;
import com.cardio.physician.domain.tutorial.TutorialModel;
import com.cardio.physician.ui.views.auth.AuthenticateUserActivity;
import com.cardio.physician.ui.views.tutorial.adapter.SliderAdapter;
import dagger.hilt.android.AndroidEntryPoint;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\fH\u0002J\u0012\u0010\u0016\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u0012H\u0002J\b\u0010\u001a\u001a\u00020\u0012H\u0002J\b\u0010\u001b\u001a\u00020\u0012H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/cardio/physician/ui/views/tutorial/TutorialActivity;", "Lcom/cardio/physician/ui/common/base/activity/BaseActivity;", "()V", "binding", "Lcom/cardio/physician/databinding/ActivityTutorialBinding;", "getBinding", "()Lcom/cardio/physician/databinding/ActivityTutorialBinding;", "binding$delegate", "Lkotlin/Lazy;", "changeListener", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "currentPos", "", "tutorialImages", "Ljava/util/ArrayList;", "tutorialModel", "Lcom/cardio/physician/domain/tutorial/TutorialModel;", "btnVisibility", "", "visibilityGetStarted", "visibilityNext", "skipVisibilty", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "openUserAuthActivity", "setAdapter", "setListeners", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class TutorialActivity extends com.cardio.physician.ui.common.base.activity.BaseActivity {
    private final kotlin.Lazy binding$delegate = null;
    private com.cardio.physician.domain.tutorial.TutorialModel tutorialModel;
    private int currentPos = 0;
    private final java.util.ArrayList<java.lang.Integer> tutorialImages = null;
    private androidx.viewpager.widget.ViewPager.OnPageChangeListener changeListener;
    
    public TutorialActivity() {
        super();
    }
    
    private final com.cardio.physician.databinding.ActivityTutorialBinding getBinding() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setAdapter() {
    }
    
    private final void setListeners() {
    }
    
    private final void openUserAuthActivity() {
    }
    
    private final void btnVisibility(int visibilityGetStarted, int visibilityNext, int skipVisibilty) {
    }
}
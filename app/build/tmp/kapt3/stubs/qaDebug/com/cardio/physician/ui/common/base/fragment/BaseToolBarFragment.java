package com.cardio.physician.ui.common.base.fragment;

import android.os.Bundle;
import android.view.View;
import androidx.viewbinding.ViewBinding;
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&J\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0002\u00a8\u0006\u000e"}, d2 = {"Lcom/cardio/physician/ui/common/base/fragment/BaseToolBarFragment;", "Binding", "Landroidx/viewbinding/ViewBinding;", "Lcom/cardio/physician/ui/common/base/fragment/BaseFragment;", "()V", "getToolbarImp", "Lcom/cardio/physician/ui/common/customviews/toolbar/base/IToolbar;", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setUpToolbar", "app_qaDebug"})
public abstract class BaseToolBarFragment<Binding extends androidx.viewbinding.ViewBinding> extends com.cardio.physician.ui.common.base.fragment.BaseFragment<Binding> {
    
    public BaseToolBarFragment() {
        super();
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setUpToolbar(com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar view) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar getToolbarImp();
}
package com.cardio.physician.ui.views.profile.setting;

import android.os.Bundle;
import android.view.View;
import com.cardio.physician.R;
import com.cardio.physician.databinding.FragmentSettingBinding;
import com.cardio.physician.domain.user.SignUpUserType;
import com.cardio.physician.network.NetworkHelper;
import com.cardio.physician.ui.common.base.activity.BaseActivity;
import com.cardio.physician.ui.common.base.fragment.BaseFragmentAuth;
import com.cardio.physician.ui.common.utils.WEBURL;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0012\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u001a\u0010!\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0018\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020&H\u0002J\b\u0010(\u001a\u00020\u001eH\u0002J\b\u0010)\u001a\u00020\u001eH\u0002J\b\u0010*\u001a\u00020\u001eH\u0002R#\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006+"}, d2 = {"Lcom/cardio/physician/ui/views/profile/setting/SettingFragment;", "Lcom/cardio/physician/ui/common/base/fragment/BaseFragmentAuth;", "Landroid/view/View$OnClickListener;", "()V", "binding", "Lcom/cardio/physician/databinding/FragmentSettingBinding;", "kotlin.jvm.PlatformType", "getBinding", "()Lcom/cardio/physician/databinding/FragmentSettingBinding;", "binding$delegate", "Lcom/cardio/physician/ui/common/utils/viewbinding/FragmentViewBindingDelegate;", "navArgs", "Lcom/cardio/physician/ui/views/profile/setting/SettingFragmentArgs;", "getNavArgs", "()Lcom/cardio/physician/ui/views/profile/setting/SettingFragmentArgs;", "navArgs$delegate", "Landroidx/navigation/NavArgsLazy;", "networkHelper", "Lcom/cardio/physician/network/NetworkHelper;", "getNetworkHelper", "()Lcom/cardio/physician/network/NetworkHelper;", "setNetworkHelper", "(Lcom/cardio/physician/network/NetworkHelper;)V", "viewModel", "Lcom/cardio/physician/ui/views/profile/setting/SettingViewModel;", "getViewModel", "()Lcom/cardio/physician/ui/views/profile/setting/SettingViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onClick", "", "view", "Landroid/view/View;", "onViewCreated", "savedInstanceState", "Landroid/os/Bundle;", "openWebUrl", "toolbarTitle", "", "webUrl", "setListener", "setObservers", "setViews", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class SettingFragment extends com.cardio.physician.ui.common.base.fragment.BaseFragmentAuth implements android.view.View.OnClickListener {
    private final com.cardio.physician.ui.common.utils.viewbinding.FragmentViewBindingDelegate binding$delegate = null;
    private final kotlin.Lazy viewModel$delegate = null;
    private final androidx.navigation.NavArgsLazy navArgs$delegate = null;
    @javax.inject.Inject()
    public com.cardio.physician.network.NetworkHelper networkHelper;
    
    public SettingFragment() {
        super(0);
    }
    
    private final com.cardio.physician.databinding.FragmentSettingBinding getBinding() {
        return null;
    }
    
    private final com.cardio.physician.ui.views.profile.setting.SettingViewModel getViewModel() {
        return null;
    }
    
    private final com.cardio.physician.ui.views.profile.setting.SettingFragmentArgs getNavArgs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.network.NetworkHelper getNetworkHelper() {
        return null;
    }
    
    public final void setNetworkHelper(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.network.NetworkHelper p0) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setViews() {
    }
    
    private final void setListener() {
    }
    
    private final void setObservers() {
    }
    
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.Nullable()
    android.view.View view) {
    }
    
    private final void openWebUrl(java.lang.String toolbarTitle, java.lang.String webUrl) {
    }
}
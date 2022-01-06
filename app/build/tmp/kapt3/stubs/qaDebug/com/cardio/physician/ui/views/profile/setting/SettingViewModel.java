package com.cardio.physician.ui.views.profile.setting;

import com.cardio.physician.domain.fitness.FitnessRepositary;
import com.cardio.physician.ui.common.base.viewmodel.BaseViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;
import javax.inject.Named;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/cardio/physician/ui/views/profile/setting/SettingViewModel;", "Lcom/cardio/physician/ui/common/base/viewmodel/BaseViewModel;", "fitbit", "Lcom/cardio/physician/domain/fitness/FitnessRepositary;", "(Lcom/cardio/physician/domain/fitness/FitnessRepositary;)V", "getFitbit", "()Lcom/cardio/physician/domain/fitness/FitnessRepositary;", "logout", "", "app_qaDebug"})
public final class SettingViewModel extends com.cardio.physician.ui.common.base.viewmodel.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.domain.fitness.FitnessRepositary fitbit = null;
    
    @javax.inject.Inject()
    public SettingViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "FITBIT")
    com.cardio.physician.domain.fitness.FitnessRepositary fitbit) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.fitness.FitnessRepositary getFitbit() {
        return null;
    }
    
    public final void logout() {
    }
}
package com.cardio.physician.ui.views.add_patient;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import com.cardio.physician.data.local.UserManager;
import com.cardio.physician.data.remote.addpatient.AddPatientRepositoryImp;
import com.cardio.physician.domain.addpatient.PatientModel;
import com.cardio.physician.network.Resource;
import com.cardio.physician.network.api.Constants;
import com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel;
import com.cardio.physician.ui.common.utils.*;
import dagger.hilt.android.lifecycle.HiltViewModel;
import java.lang.Exception;
import java.util.HashMap;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Suppress(names = {"UNUSED_PARAMETER"})
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0006\u0010\u0016\u001a\u00020\u0013J\u000e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u000e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR \u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b0\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/cardio/physician/ui/views/add_patient/AddPatientViewModel;", "Lcom/cardio/physician/ui/common/base/viewmodel/BaseAuthViewModel;", "userManager", "Lcom/cardio/physician/data/local/UserManager;", "addPatientRepositoryImp", "Lcom/cardio/physician/data/remote/addpatient/AddPatientRepositoryImp;", "application", "Landroid/app/Application;", "(Lcom/cardio/physician/data/local/UserManager;Lcom/cardio/physician/data/remote/addpatient/AddPatientRepositoryImp;Landroid/app/Application;)V", "liveUserData", "Landroidx/lifecycle/LiveData;", "Lcom/cardio/physician/network/Resource;", "", "Lcom/cardio/physician/domain/addpatient/PatientModel;", "getLiveUserData", "()Landroidx/lifecycle/LiveData;", "userSearchSingleLiveData", "Landroidx/lifecycle/MediatorLiveData;", "checkValidEmailAddress", "", "searchString", "", "getAllPatientList", "searchUserInFirestore", "searchUserInFirestoreWithEmail", "updateDataToFirestore", "patientModel", "app_qaDebug"})
public final class AddPatientViewModel extends com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel {
    private final com.cardio.physician.data.remote.addpatient.AddPatientRepositoryImp addPatientRepositoryImp = null;
    private final androidx.lifecycle.MediatorLiveData<com.cardio.physician.network.Resource<java.util.List<com.cardio.physician.domain.addpatient.PatientModel>>> userSearchSingleLiveData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.util.List<com.cardio.physician.domain.addpatient.PatientModel>>> liveUserData = null;
    
    @javax.inject.Inject()
    public AddPatientViewModel(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.local.UserManager userManager, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.addpatient.AddPatientRepositoryImp addPatientRepositoryImp, @org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.util.List<com.cardio.physician.domain.addpatient.PatientModel>>> getLiveUserData() {
        return null;
    }
    
    public final void searchUserInFirestore(@org.jetbrains.annotations.NotNull()
    java.lang.String searchString) {
    }
    
    private final void searchUserInFirestoreWithEmail(java.lang.String searchString) {
    }
    
    private final void checkValidEmailAddress(java.lang.String searchString) {
    }
    
    public final void getAllPatientList() {
    }
    
    public final void updateDataToFirestore(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.addpatient.PatientModel patientModel) {
    }
}
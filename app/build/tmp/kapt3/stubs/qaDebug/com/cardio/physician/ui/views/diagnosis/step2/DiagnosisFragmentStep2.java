package com.cardio.physician.ui.views.diagnosis.step2;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.GridLayoutManager;
import com.cardio.physician.R;
import com.cardio.physician.databinding.FragmentDiagnosisPart2Binding;
import com.cardio.physician.domain.diagnosis.MedicineModel;
import com.cardio.physician.ui.common.utils.Constants;
import com.cardio.physician.ui.common.utils.ItemOffsetDecoration;
import com.cardio.physician.ui.views.diagnosis.common.BaseDiagnosisFragment;
import com.cardio.physician.ui.views.diagnosis.step1.DiagnosisFragmentStep1Args;
import com.cardio.physician.ui.views.diagnosis.step2.adapter.MedRecylarAdapter;
import com.cardio.physician.ui.views.diagnosis.step2.adapter.PredictiveMedicineSearchAdapter;
import com.google.mlkit.vision.demo.kotlin.StillImageActivity;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.*;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0017\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0019H\u0016J\b\u0010*\u001a\u00020(H\u0002J\b\u0010+\u001a\u00020(H\u0002J\b\u0010,\u001a\u00020(H\u0002J\b\u0010-\u001a\u00020(H\u0002J\u0010\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u000200H\u0002J\u0010\u00101\u001a\u00020(2\u0006\u00102\u001a\u000203H\u0002J\b\u00104\u001a\u00020(H\u0016J&\u00105\u001a\u0004\u0018\u0001062\u0006\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010:2\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J\b\u0010=\u001a\u00020(H\u0016J\b\u0010>\u001a\u00020(H\u0002J\u001a\u0010?\u001a\u00020(2\u0006\u0010@\u001a\u0002062\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J\b\u0010A\u001a\u00020(H\u0002J\u0016\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u0017\u001a\u00020\u0014H\u0002J\b\u0010C\u001a\u00020(H\u0002J\b\u0010D\u001a\u00020(H\u0016J\b\u0010E\u001a\u00020(H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR/\u0010\u0012\u001a#\u0012\u0013\u0012\u00110\u0014\u00a2\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010\u001a\u001a\u0016\u0012\u0012\u0012\u0010\u0012\f\u0012\n \u001d*\u0004\u0018\u00010\u00140\u00140\u001c0\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010\u001e\u001a\u0016\u0012\u0012\u0012\u0010\u0012\f\u0012\n \u001d*\u0004\u0018\u00010\u00140\u00140\u001c0\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010!\u001a\u0010\u0012\f\u0012\n \u001d*\u0004\u0018\u00010 0 0\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\"\u001a\u00020#8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b&\u0010\t\u001a\u0004\b$\u0010%\u00a8\u0006F"}, d2 = {"Lcom/cardio/physician/ui/views/diagnosis/step2/DiagnosisFragmentStep2;", "Lcom/cardio/physician/ui/views/diagnosis/common/BaseDiagnosisFragment;", "Lcom/cardio/physician/databinding/FragmentDiagnosisPart2Binding;", "()V", "adapterMed", "Lcom/cardio/physician/ui/views/diagnosis/step2/adapter/MedRecylarAdapter;", "getAdapterMed", "()Lcom/cardio/physician/ui/views/diagnosis/step2/adapter/MedRecylarAdapter;", "adapterMed$delegate", "Lkotlin/Lazy;", "adapterPridictiveSearch", "Lcom/cardio/physician/ui/views/diagnosis/step2/adapter/PredictiveMedicineSearchAdapter;", "args", "Lcom/cardio/physician/ui/views/diagnosis/step2/DiagnosisFragmentStep2Args;", "getArgs", "()Lcom/cardio/physician/ui/views/diagnosis/step2/DiagnosisFragmentStep2Args;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "onMedicineSearch", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "query", "", "Lcom/cardio/physician/domain/diagnosis/MedicineModel;", "requestSpeechPermissions", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "requestStoragePermissions", "resultLauncherSpeechToText", "Landroid/content/Intent;", "resultTextRecog", "viewModel", "Lcom/cardio/physician/ui/views/diagnosis/step2/DiagnosisStep2ViewModel;", "getViewModel", "()Lcom/cardio/physician/ui/views/diagnosis/step2/DiagnosisStep2ViewModel;", "viewModel$delegate", "addToList", "", "it", "checkSpeechPermission", "checkStoragePermission", "init", "launchSpeechToText", "medSearchProgressVisibility", "visibility", "", "onAddMed", "isPreExistedMed", "", "onBottomBackButtonClick", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onNextButtonClick", "onStoragePermissionGranted", "onViewCreated", "view", "promptToInstallApp", "searchMedicine", "setListeners", "setObservers", "setViews", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public class DiagnosisFragmentStep2 extends com.cardio.physician.ui.views.diagnosis.common.BaseDiagnosisFragment<com.cardio.physician.databinding.FragmentDiagnosisPart2Binding> {
    private com.cardio.physician.ui.views.diagnosis.step2.adapter.PredictiveMedicineSearchAdapter adapterPridictiveSearch;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private final kotlin.Lazy adapterMed$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.navigation.NavArgsLazy args$delegate = null;
    private final kotlin.jvm.functions.Function1<java.lang.String, java.util.List<com.cardio.physician.domain.diagnosis.MedicineModel>> onMedicineSearch = null;
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> resultLauncherSpeechToText;
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String[]> requestSpeechPermissions = null;
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String[]> requestStoragePermissions = null;
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> resultTextRecog = null;
    
    public DiagnosisFragmentStep2() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.views.diagnosis.step2.DiagnosisStep2ViewModel getViewModel() {
        return null;
    }
    
    private final com.cardio.physician.ui.views.diagnosis.step2.adapter.MedRecylarAdapter getAdapterMed() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.views.diagnosis.step2.DiagnosisFragmentStep2Args getArgs() {
        return null;
    }
    
    private final java.util.List<com.cardio.physician.domain.diagnosis.MedicineModel> searchMedicine(java.lang.String query) {
        return null;
    }
    
    private final void medSearchProgressVisibility(int visibility) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void init() {
    }
    
    private final void setViews() {
    }
    
    private final void onStoragePermissionGranted() {
    }
    
    private final void launchSpeechToText() {
    }
    
    private final void promptToInstallApp() {
    }
    
    private final void setListeners() {
    }
    
    private final void onAddMed(boolean isPreExistedMed) {
    }
    
    public void onBottomBackButtonClick() {
    }
    
    public void onNextButtonClick() {
    }
    
    private final void checkStoragePermission() {
    }
    
    private final void checkSpeechPermission() {
    }
    
    public void setObservers() {
    }
    
    public void addToList(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.diagnosis.MedicineModel it) {
    }
}
package com.cardio.physician.ui.common.utils.extentions;

import android.os.Parcel;
import com.cardio.physician.domain.common.model.UserModel;
import com.cardio.physician.domain.connection.ConnectionModel;
import com.cardio.physician.domain.diagnosis.DiagnosisModel;
import com.cardio.physician.domain.diagnosis.MedicineModel;
import com.cardio.physician.domain.questionare.model.QuestionModel;
import com.cardio.physician.domain.user.SignUpUserType;
import com.cardio.physician.domain.user.UserType;
import com.cardio.physician.ui.common.utils.FireStoreDocKey;
import com.cardio.physician.domain.addpatient.PatientModel;
import com.cardio.physician.domain.fitness.model.FitnessModel;
import com.cardio.physician.domain.notifications.NotificationModel;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

@kotlin.Metadata(mv = {1, 5, 1}, k = 2, d1 = {"\u0000L\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003\u001a\u0010\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u0001*\u00020\u0003\u001a\u0010\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001*\u00020\u0003\u001a\n\u0010\b\u001a\u00020\t*\u00020\n\u001a\u0010\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001*\u00020\u0003\u001a\u0012\u0010\r\u001a\u00020\u000e*\u00020\n2\u0006\u0010\u000f\u001a\u00020\f\u001a\u0010\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0001*\u00020\u0003\u001a\u0010\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003\u001a\u0010\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0001*\u00020\u0003\u001a\n\u0010\u0015\u001a\u00020\u0016*\u00020\u0017\u001a\n\u0010\u0015\u001a\u00020\u0016*\u00020\n\u00a8\u0006\u0018"}, d2 = {"toCPatientModel", "", "Lcom/cardio/physician/domain/addpatient/PatientModel;", "Lcom/google/firebase/firestore/QuerySnapshot;", "toConnectionModel", "Lcom/cardio/physician/domain/connection/ConnectionModel;", "toDiagnosisModel", "Lcom/cardio/physician/domain/diagnosis/DiagnosisModel;", "toFitNessModel", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "Lcom/google/firebase/firestore/DocumentSnapshot;", "toMedicineListModel", "Lcom/cardio/physician/domain/diagnosis/MedicineModel;", "toMedicineModel", "", "medicineModel", "toNotificationsList", "Lcom/cardio/physician/domain/notifications/NotificationModel;", "toPatientModel", "toQuestionModel", "Lcom/cardio/physician/domain/questionare/model/QuestionModel;", "toUserModel", "Lcom/cardio/physician/domain/common/model/UserModel;", "Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;", "app_qaDebug"})
public final class FirebaseExtentionsKt {
    
    @org.jetbrains.annotations.NotNull()
    public static final com.cardio.physician.domain.common.model.UserModel toUserModel(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.auth.api.signin.GoogleSignInAccount $this$toUserModel) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final com.cardio.physician.domain.common.model.UserModel toUserModel(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.DocumentSnapshot $this$toUserModel) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.util.List<com.cardio.physician.domain.questionare.model.QuestionModel> toQuestionModel(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.QuerySnapshot $this$toQuestionModel) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.util.List<com.cardio.physician.domain.diagnosis.DiagnosisModel> toDiagnosisModel(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.QuerySnapshot $this$toDiagnosisModel) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.util.List<com.cardio.physician.domain.diagnosis.MedicineModel> toMedicineListModel(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.QuerySnapshot $this$toMedicineListModel) {
        return null;
    }
    
    public static final void toMedicineModel(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.DocumentSnapshot $this$toMedicineModel, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.diagnosis.MedicineModel medicineModel) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.util.List<com.cardio.physician.domain.addpatient.PatientModel> toPatientModel(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.QuerySnapshot $this$toPatientModel) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.util.List<com.cardio.physician.domain.addpatient.PatientModel> toCPatientModel(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.QuerySnapshot $this$toCPatientModel) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.util.List<com.cardio.physician.domain.connection.ConnectionModel> toConnectionModel(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.QuerySnapshot $this$toConnectionModel) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final com.cardio.physician.domain.fitness.model.FitnessModel toFitNessModel(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.DocumentSnapshot $this$toFitNessModel) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.util.List<com.cardio.physician.domain.notifications.NotificationModel> toNotificationsList(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.QuerySnapshot $this$toNotificationsList) {
        return null;
    }
}
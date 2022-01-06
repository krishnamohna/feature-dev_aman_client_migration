package com.cardio.physician.data.remote.questionnaire;

import com.cardio.physician.domain.questionare.QuestionnaireRepo;
import com.cardio.physician.domain.questionare.model.QuestionModel;
import com.cardio.physician.ui.common.utils.FireStoreCollection;
import com.cardio.physician.ui.common.utils.FireStoreDocKey;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.gson.Gson;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u001f\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\r"}, d2 = {"Lcom/cardio/physician/data/remote/questionnaire/QuestionnaireRepoImp;", "Lcom/cardio/physician/domain/questionare/QuestionnaireRepo;", "fireStore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "(Lcom/google/firebase/firestore/FirebaseFirestore;)V", "getQuestionnaires", "", "Lcom/cardio/physician/domain/questionare/model/QuestionModel;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveQuestionaire", "", "questions", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_qaDebug"})
public final class QuestionnaireRepoImp implements com.cardio.physician.domain.questionare.QuestionnaireRepo {
    private final com.google.firebase.firestore.FirebaseFirestore fireStore = null;
    
    @javax.inject.Inject()
    public QuestionnaireRepoImp(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore fireStore) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getQuestionnaires(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.cardio.physician.domain.questionare.model.QuestionModel>> p0) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveQuestionaire(@org.jetbrains.annotations.NotNull()
    java.util.List<com.cardio.physician.domain.questionare.model.QuestionModel> questions, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
}
package com.cardio.doctor.data.remote.questionnaire

import com.cardio.doctor.domain.questionare.QuestionnaireRepo
import com.cardio.doctor.domain.questionare.model.QuestionModel
import com.cardio.doctor.ui.common.utils.FireStoreCollection
import com.cardio.doctor.ui.common.utils.FireStoreDocKey
import com.cardio.doctor.ui.common.utils.extentions.toQuestionModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class QuestionnaireRepoImp @Inject constructor(
    private val fireStore: FirebaseFirestore,
) :
    QuestionnaireRepo {

    override suspend fun getQuestionnaires(): List<QuestionModel> {
        var result = fireStore.collection(FireStoreCollection.QUESTIONNAIRE)
            .document(FireStoreDocKey.CARDIAC_HEART_FAILURE).collection(FireStoreDocKey.QUESTIONS)
            .orderBy(FireStoreDocKey.POSITION, Query.Direction.ASCENDING)
            .get().await()
        return result.toQuestionModel()
    }
}
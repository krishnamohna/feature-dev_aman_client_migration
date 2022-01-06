package com.cardio.physician.data.remote.questionnaire

import com.cardio.physician.domain.questionare.QuestionnaireRepo
import com.cardio.physician.domain.questionare.model.QuestionModel
import com.cardio.physician.ui.common.utils.FireStoreCollection
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import com.cardio.physician.ui.common.utils.extentions.toQuestionModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.gson.Gson
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class QuestionnaireRepoImp @Inject constructor(
    private val fireStore: FirebaseFirestore,
) :
    QuestionnaireRepo {

    /*this method is used for showing questions on third step of diagnosis */
    override suspend fun getQuestionnaires(): List<QuestionModel> {
        var result = fireStore.collection(FireStoreCollection.QUESTIONNAIRE)
            .document(FireStoreDocKey.CARDIAC_HEART_FAILURE).collection(FireStoreDocKey.QUESTIONS)
            .orderBy(FireStoreDocKey.POSITION, Query.Direction.ASCENDING)
            .get().await()
        return result.toQuestionModel()
    }

    // method is only for coping question to diffrent collection. user this method to copy questionare to somet other collections
    suspend fun saveQuestionaire(questions: List<QuestionModel>) {
        questions.forEachIndexed { index, questionModel ->
            var json = Gson().toJson(questionModel)
            var diagnosis: Map<String, Any> = HashMap()
            diagnosis = Gson().fromJson(json, diagnosis.javaClass)
            fireStore.collection(FireStoreCollection.QUESTIONNAIRE)
                .document("Heart Failure")
                .collection("Questions").document((index + 1).toString())
                .set(diagnosis)
                .await()
        }
    }
}
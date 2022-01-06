package com.cardio.physician.ui.views.diagnosis

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cardio.physician.R
import com.cardio.physician.domain.diagnosis.DiagnosisModel
import com.cardio.physician.ui.common.customviews.StepView
import com.cardio.physician.ui.common.utils.EXTRAS
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditDiagnosisActivity : DiagnosisActivity() {

    companion object {
        const val EDIT_BASIC_INFO = 1
        const val EDIT_DIAGNOSIS_INFO = 2
        const val EDIT_DIAGNOSIS_MEDICATION = 3
        /*fun start(activity: Activity, diagnosisModel: DiagnosisModel, editType: Int) {
            getIntent(activity, diagnosisModel, editType).run {
                activity?.startActivity(this)
            }
            fun start(activity: Activity) {
                activity?.startActivity(getActivityIntent(activity))
            }

            fun getActivityIntent(activity: Activity) =
                Intent(activity, DiagnosisActivity::class.java)
        }*/

        fun getIntent(activity: Activity, diagnosisModel: DiagnosisModel, editType: Int, userId: String?) =
            Intent(activity, EditDiagnosisActivity::class.java).apply {
                putExtra(EXTRAS.DIAGNOSIS_MODEL, diagnosisModel)
                putExtra(EXTRAS.EDIT_TYPE, editType)
                putExtra(EXTRAS.USER_ID, userId)
            }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        intent.getParcelableExtra<DiagnosisModel>(EXTRAS.DIAGNOSIS_MODEL)?.let {
            diagnosisModel = it
            questionList = diagnosisModel.questionnaire
        }
        super.onCreate(savedInstanceState)
    }

    /*hide step indicator in case of edit diagnosis*/
    override fun setStepView(stepView: StepView) {
        super.setStepView(stepView)
        stepView.visibility = View.GONE
    }

    /*set different nav graph incase of editing diagnosis */
    override fun setNavGraph() {
        when (intent.getIntExtra(EXTRAS.EDIT_TYPE, EDIT_BASIC_INFO)) {
            EDIT_BASIC_INFO -> {
                val bundle = Bundle()
                bundle.putString(FireStoreDocKey.USER_ID, intent.getStringExtra(EXTRAS.USER_ID))
                navController.setGraph(R.navigation.graph_diagnosis_edit_basic_info,bundle)
            }
            EDIT_DIAGNOSIS_INFO -> {
                val bundle = Bundle()
                bundle.putString(FireStoreDocKey.USER_ID, intent.getStringExtra(EXTRAS.USER_ID))
                navController.setGraph(R.navigation.graph_diagnosis_edit_questionare_info,bundle)
            }
            EDIT_DIAGNOSIS_MEDICATION-> {
                val bundle = Bundle()
                bundle.putString(FireStoreDocKey.USER_ID, intent.getStringExtra(EXTRAS.USER_ID))
                navController.setGraph(R.navigation.graph_diagnosis_edit_medication,bundle)
            }
        }
    }

}
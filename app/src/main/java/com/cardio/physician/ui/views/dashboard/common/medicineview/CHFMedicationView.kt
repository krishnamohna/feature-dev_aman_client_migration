package com.cardio.physician.ui.views.dashboard.common.medicineview

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.content.res.ResourcesCompat
import com.cardio.physician.R
import com.cardio.physician.databinding.FragmentDashboardBinding
import com.cardio.physician.domain.diagnosis.DiagnosisModel
import com.cardio.physician.ui.common.utils.extentions.removeSpace
import com.google.android.flexbox.FlexboxLayout

class CHFMedicationView : IMedicineView {

    private fun getContext(binding: FragmentDashboardBinding) = binding.root.context

    override fun showMedicationData(
        diagnosisModel: DiagnosisModel,
        binding: FragmentDashboardBinding,
    ) {
        binding.includeChbMedications.cvChfMed.visibility = View.VISIBLE
        binding.includeBasicInfo.cvPatienDetail.visibility = View.VISIBLE
        binding.tvCvMedicationCategorized.visibility = View.VISIBLE
        //set data for chif medications
        setChefMedicatingData(diagnosisModel, binding)
    }

    private fun setChefMedicatingData(
        diagnosisModel: DiagnosisModel,
        binding: FragmentDashboardBinding,
    ) {
        //remove all views from containers
        removeAllMedicines(binding)
        //hid other container
        //show other container when any medicine is found
        visibleOtherContainer(View.GONE, binding)
        val betaBlocker =
            getContext(binding).resources.getString(R.string.beta_blocker).removeSpace()
        val arni = getContext(binding).resources.getString(R.string.arni).removeSpace()
        val sglt2_inhibitor =
            getContext(binding).resources.getString(R.string.sglt2_inhibitor).removeSpace()
        val ace_arb = getContext(binding).resources.getString(R.string.ace_arb).removeSpace()
        val mra = getContext(binding).resources.getString(R.string.mra).removeSpace()
        val diuretic = getContext(binding).resources.getString(R.string.diuretic).removeSpace()
        diagnosisModel?.medications?.let {
            if (it.isNotEmpty()) {
                it.forEachIndexed { index, medicineModel ->
                    if (medicineModel.category.isNullOrBlank()) {
                        visibleOtherContainer(View.VISIBLE, binding)
                        addMedicineView(binding.includeChbMedications.flOther,
                            getMedicineView(getContext(binding), medicineModel.name))
                    } else {
                        when {
                            medicineModel.category!!.removeSpace().equals(betaBlocker,
                                true) -> {
                                addMedicineView(binding.includeChbMedications.flBetaBlocker,
                                    getMedicineView(getContext(binding), medicineModel.name))
                            }
                            medicineModel.category!!.removeSpace().equals(arni,
                                true) -> {
                                addMedicineView(binding.includeChbMedications.flArni,
                                    getMedicineView(getContext(binding), medicineModel.name))
                            }
                            medicineModel.category!!.removeSpace().equals(sglt2_inhibitor,
                                true) -> {
                                addMedicineView(binding.includeChbMedications.flSgl,
                                    getMedicineView(getContext(binding), medicineModel.name))
                            }
                            medicineModel.category!!.removeSpace().equals(ace_arb,
                                true) -> {
                                addMedicineView(binding.includeChbMedications.flAce,
                                    getMedicineView(getContext(binding), medicineModel.name))
                            }
                            medicineModel.category!!.removeSpace().equals(mra,
                                true) -> {
                                addMedicineView(binding.includeChbMedications.flMra,
                                    getMedicineView(getContext(binding), medicineModel.name))
                            }
                            else -> {
                                visibleOtherContainer(View.VISIBLE, binding)
                                addMedicineView(binding.includeChbMedications.flOther,
                                    getMedicineView(getContext(binding), medicineModel.name))
                            }
                        }

                    }
                }
            }
        }
        //add none medication to categories which are empty
        addNoneMed(binding)
    }

    private fun visibleOtherContainer(visibile: Int, binding: FragmentDashboardBinding) {
        binding.includeChbMedications.tvOtherLabel.visibility = visibile
        binding.includeChbMedications.flOther.visibility = visibile
    }

    private fun removeAllMedicines(binding: FragmentDashboardBinding) {
        binding.includeChbMedications.flBetaBlocker.removeAllViews()
        binding.includeChbMedications.flArni.removeAllViews()
        binding.includeChbMedications.flSgl.removeAllViews()
        binding.includeChbMedications.flAce.removeAllViews()
        binding.includeChbMedications.flMra.removeAllViews()
        binding.includeChbMedications.flOther.removeAllViews()
    }

    private fun addNoneMed(binding: FragmentDashboardBinding) {
        if (binding.includeChbMedications.flBetaBlocker.childCount == 0) addMedicineView(binding.includeChbMedications.flBetaBlocker,
            getNoneMedicineView(getContext(binding), getContext(binding).getString(R.string.none)))
        if (binding.includeChbMedications.flArni.childCount == 0) addMedicineView(binding.includeChbMedications.flArni,
            getNoneMedicineView(getContext(binding), getContext(binding).getString(R.string.none)))
        if (binding.includeChbMedications.flSgl.childCount == 0) addMedicineView(binding.includeChbMedications.flSgl,
            getNoneMedicineView(getContext(binding), getContext(binding).getString(R.string.none)))
        if (binding.includeChbMedications.flAce.childCount == 0) addMedicineView(binding.includeChbMedications.flAce,
            getNoneMedicineView(getContext(binding), getContext(binding).getString(R.string.none)))
        if (binding.includeChbMedications.flMra.childCount == 0) addMedicineView(binding.includeChbMedications.flMra,
            getNoneMedicineView(getContext(binding), getContext(binding).getString(R.string.none)))
        if (binding.includeChbMedications.flOther.childCount == 0) addMedicineView(binding.includeChbMedications.flOther,
            getNoneMedicineView(getContext(binding), getContext(binding).getString(R.string.none)))
    }

    private fun getMedicineView(
        context: Context,
        drugName: String?,
        style: Int = R.style.BaseAppTheme_TxtView_bold_medication,
    ): View {
        return TextView(ContextThemeWrapper(context, style)).apply {
            typeface = ResourcesCompat.getFont(context, R.font.nunito_bold)
            drugName.also { text = it }
        }
    }

    private fun getNoneMedicineView(context: Context, drugName: String?): View {
        return getMedicineView(context, drugName, R.style.BaseAppTheme_TxtView_bold_medication_none)
    }

    private fun addMedicineView(flLayout: FlexboxLayout, medicineView: View) {
        var margin2dp = flLayout.context.resources.getDimension(R.dimen._2sdp).toInt()
        var height = flLayout.context.resources.getDimension(R.dimen.medicine_height).toInt()
        var layoutParams = FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height)
        layoutParams.setMargins(margin2dp, 0, 0, margin2dp)
        flLayout.addView(medicineView, layoutParams)
    }

}
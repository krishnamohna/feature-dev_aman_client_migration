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

class AFibMedicationView : IMedicineView {

    private fun getContext(binding: FragmentDashboardBinding) = binding.root.context

    override fun showMedicationData(diagnosisModel: DiagnosisModel, binding: FragmentDashboardBinding) {
        binding.includeAfibMedications.cvAfibMed.visibility = View.VISIBLE
        binding.includeBasicInfo.cvPatienDetail.visibility = View.VISIBLE
        binding.tvCvMedicationCategorized.visibility = View.VISIBLE
        //set data for afib medications
        setAfibMedicatingData(diagnosisModel, binding)
    }

    private fun setAfibMedicatingData(
        diagnosisModel: DiagnosisModel,
        binding: FragmentDashboardBinding,
    ) {
        //remove all views from containers
        removeAllMedicines(binding)
        var antiplatelet =getContext(binding).resources.getString(R.string.antiplatelet).removeSpace()
        var anticoagulant =getContext(binding).resources.getString(R.string.anticoagulant).removeSpace()
        var antiarrhythmic =getContext(binding).resources.getString(R.string.antiarrhythmic).removeSpace()
        var rate_control_agent =getContext(binding).resources.getString(R.string.rate_control_agent).removeSpace()
        diagnosisModel?.medications?.let {
            if (it.isNotEmpty()) {
                it.forEachIndexed { index, medicineModel ->
                    if (medicineModel.category.isNullOrBlank()) {
                        //add medicine to other
                        addMedicineView(binding.includeAfibMedications.flOther,
                            getMedicineView(getContext(binding), medicineModel.name))
                    } else {
                        when{
                            medicineModel.category!!.removeSpace().equals(antiplatelet,
                                true)->{
                                addMedicineView(binding.includeAfibMedications.flAntiplatelet,
                                    getMedicineView(getContext(binding), medicineModel.name))
                            }
                            medicineModel.category!!.removeSpace().equals(anticoagulant,
                                true)->{
                                addMedicineView(binding.includeAfibMedications.flAnticoagulant,
                                    getMedicineView(getContext(binding), medicineModel.name))
                            }
                            medicineModel.category!!.removeSpace().equals(antiarrhythmic,
                                true)->{
                                addMedicineView(binding.includeAfibMedications.flAntiarrhythmic,
                                    getMedicineView(getContext(binding), medicineModel.name))
                            }
                            medicineModel.category!!.removeSpace().equals(rate_control_agent,
                                true)->{
                                addMedicineView(binding.includeAfibMedications.flRateControlAgent,
                                    getMedicineView(getContext(binding), medicineModel.name))
                            }
                            else->{
                                addMedicineView(binding.includeAfibMedications.flOther,
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

    private fun removeAllMedicines(binding: FragmentDashboardBinding) {
        binding.includeAfibMedications.flAntiplatelet.removeAllViews()
        binding.includeAfibMedications.flAnticoagulant.removeAllViews()
        binding.includeAfibMedications.flAntiarrhythmic.removeAllViews()
        binding.includeAfibMedications.flRateControlAgent.removeAllViews()
        binding.includeAfibMedications.flOther.removeAllViews()
    }

    private fun addNoneMed(binding: FragmentDashboardBinding) {
        if (binding.includeAfibMedications.flAntiplatelet.childCount == 0) addMedicineView(binding.includeAfibMedications.flAntiplatelet,
            getNoneMedicineView(getContext(binding), getContext(binding).getString(R.string.none)))
        if (binding.includeAfibMedications.flAnticoagulant.childCount == 0) addMedicineView(binding.includeAfibMedications.flAnticoagulant,
            getNoneMedicineView(getContext(binding), getContext(binding).getString(R.string.none)))
        if (binding.includeAfibMedications.flAntiarrhythmic.childCount == 0) addMedicineView(binding.includeAfibMedications.flAntiarrhythmic,
            getNoneMedicineView(getContext(binding), getContext(binding).getString(R.string.none)))
        if (binding.includeAfibMedications.flRateControlAgent.childCount == 0) addMedicineView(binding.includeAfibMedications.flRateControlAgent,
            getNoneMedicineView(getContext(binding), getContext(binding).getString(R.string.none)))
        if (binding.includeAfibMedications.flOther.childCount == 0) addMedicineView(binding.includeAfibMedications.flOther,
            getNoneMedicineView(getContext(binding), getContext(binding).getString(R.string.none)))
    }

    private fun getMedicineView(context: Context, drugName: String?,style:Int=R.style.BaseAppTheme_TxtView_bold_medication): View {
        return TextView(ContextThemeWrapper(context, style)).apply {
            typeface = ResourcesCompat.getFont(context, R.font.nunito_bold)
            text = drugName
        }
    }

    private fun getNoneMedicineView(context: Context, drugName: String?): View {
        return getMedicineView(context,drugName,R.style.BaseAppTheme_TxtView_bold_medication_none)
    }

    private fun addMedicineView(flLayout: FlexboxLayout, medicineView: View) {
        var margin2dp=flLayout.context.resources.getDimension(R.dimen._2sdp).toInt()
        var height=flLayout.context.resources.getDimension(R.dimen.medicine_height).toInt()
        var layoutParams= FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height)
        layoutParams.setMargins(margin2dp,0,0,margin2dp)
        flLayout.addView(medicineView,layoutParams)
    }

}
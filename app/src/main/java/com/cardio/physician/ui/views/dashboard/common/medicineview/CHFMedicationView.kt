package com.cardio.physician.ui.views.dashboard.common.medicineview

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.content.res.ResourcesCompat
import com.cardio.physician.R
import com.cardio.physician.databinding.FragmentDashboardBinding
import com.cardio.physician.domain.diagnosis.DiagnosisModel
import com.cardio.physician.domain.diagnosis.MedicineModel
import com.cardio.physician.ui.common.utils.extentions.removeSpace
import com.cardio.physician.ui.views.dashboard.common.extenstions.isEjectionFractionEqualGreaterThan45
import com.google.android.flexbox.FlexboxLayout

class CHFMedicationView : IMedicineView {

    private lateinit var diuretic: String
    private lateinit var mra: String
    private lateinit var ace_arb: String
    private lateinit var sglt2_inhibitor: String
    private lateinit var arni: String
    private lateinit var betaBlocker: String

    private fun getContext(binding: FragmentDashboardBinding) = binding.root.context

    override fun showMedicationData(
        diagnosisModel: DiagnosisModel,
        binding: FragmentDashboardBinding,
    ) {
        binding.includeChbMedications.cvChfMed.visibility = View.VISIBLE
        binding.includeBasicInfo.cvPatienDetail.visibility = View.VISIBLE
        binding.tvCvMedicationCategorized.visibility = View.VISIBLE
        initCategories(binding)
        manageCategoryVisibilityAsPerEjectionFraction(diagnosisModel, binding)
        //set data for chif medications
        setChefMedicatingData(diagnosisModel, binding)
    }

    private fun setChefMedicatingData(
        diagnosisModel: DiagnosisModel,
        binding: FragmentDashboardBinding,
    ) {
        //remove all views from containers
        removeAllMedicines(binding)
        //show other container when any medicine is found
        visibleOtherContainer(View.GONE, binding)
        diagnosisModel.medications?.let {
            if (it.isNotEmpty()) {
                it.forEachIndexed { index, medicineModel ->
                    handleMedicine(
                        binding,
                        medicineModel, diagnosisModel
                    )
                }
            }
        }
        //add none medication to categories which are empty
        addNoneMed(binding)
    }

    private fun handleMedicine(
        binding: FragmentDashboardBinding,
        medicineModel: MedicineModel,
        diagnosisModel: DiagnosisModel
    ) {
        if (medicineModel.category.isNullOrBlank()) {
            visibleOtherContainer(View.VISIBLE, binding)
            addMedicineView(binding.includeChbMedications.flOther,
                getOtherMedicineView(getContext(binding), medicineModel.name,medicineModel))
        } else if (medicineModel.category!!.removeSpace().equals(sglt2_inhibitor,
                true)) {
            addMedicineView(binding.includeChbMedications.flSgl,
                getExistedCategoryMedicineView(getContext(binding), medicineModel.name,medicineModel))
        } else if(medicineModel.category!!.removeSpace().equals(mra,
                true)){
            addMedicineView(binding.includeChbMedications.flMra,
                getExistedCategoryMedicineView(getContext(binding), medicineModel.name,medicineModel))
        }
        else {
            //if ejection fraction is greater than 45 then do  show betablocker,arni,ace_arb med in others
            if (diagnosisModel.isEjectionFractionEqualGreaterThan45()) {
                visibleOtherContainer(View.VISIBLE, binding)
                addMedicineView(
                    binding.includeChbMedications.flOther,
                    getOtherMedicineView(getContext(binding), medicineModel.name, medicineModel)
                )
            }else if(medicineModel.category!!.removeSpace().equals(betaBlocker,
                    true)){
                addMedicineView(binding.includeChbMedications.flBetaBlocker,
                    getExistedCategoryMedicineView(getContext(binding), medicineModel.name,medicineModel))
            }
            else if(medicineModel.category!!.removeSpace().equals(arni,
                    true)){
                addMedicineView(binding.includeChbMedications.flArni,
                    getExistedCategoryMedicineView(getContext(binding), medicineModel.name,medicineModel))
            }
            else if(medicineModel.category!!.removeSpace().equals(ace_arb,
                    true)){
                addMedicineView(binding.includeChbMedications.flAce,
                    getExistedCategoryMedicineView(getContext(binding), medicineModel.name,medicineModel))
            }else{
                // if no category found then add to other
                visibleOtherContainer(View.VISIBLE, binding)
                addMedicineView(binding.includeChbMedications.flOther,
                    getOtherMedicineView(getContext(binding), medicineModel.name,medicineModel))
            }
        }
    }


    private fun visibleOtherContainer(visible: Int, binding: FragmentDashboardBinding) {
        binding.includeChbMedications.tvOtherLabel.visibility = visible
        binding.includeChbMedications.flOther.visibility = visible
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
        if (binding.includeChbMedications.flBetaBlocker.childCount == 0) addMedicineView(
            binding.includeChbMedications.flBetaBlocker,
            getNoneMedicineView(getContext(binding), getContext(binding).getString(R.string.none))
        )
        if (binding.includeChbMedications.flArni.childCount == 0) addMedicineView(
            binding.includeChbMedications.flArni,
            getNoneMedicineView(getContext(binding), getContext(binding).getString(R.string.none))
        )
        if (binding.includeChbMedications.flSgl.childCount == 0) addMedicineView(
            binding.includeChbMedications.flSgl,
            getNoneMedicineView(getContext(binding), getContext(binding).getString(R.string.none))
        )
        if (binding.includeChbMedications.flAce.childCount == 0) addMedicineView(
            binding.includeChbMedications.flAce,
            getNoneMedicineView(getContext(binding), getContext(binding).getString(R.string.none))
        )
        if (binding.includeChbMedications.flMra.childCount == 0) addMedicineView(
            binding.includeChbMedications.flMra,
            getNoneMedicineView(getContext(binding), getContext(binding).getString(R.string.none))
        )
        if (binding.includeChbMedications.flOther.childCount == 0) addMedicineView(
            binding.includeChbMedications.flOther,
            getNoneMedicineView(getContext(binding), getContext(binding).getString(R.string.none))
        )
    }

    private fun getOtherMedicineView(
        context: Context,
        drugName: String?,
        medicineModel: MedicineModel?
    ): View {
        return getMedicineView(
            context,
            drugName,
            medicineModel,
            R.style.BaseAppTheme_TxtView_bold_medication_other
        )
    }

    private fun getExistedCategoryMedicineView(
        context: Context,
        drugName: String?,
        medicineModel: MedicineModel?
    ): View {
        return getMedicineView(
            context,
            drugName,
            medicineModel,
            R.style.BaseAppTheme_TxtView_bold_medication
        )
    }

    private fun getMedicineView(
        context: Context,
        drugName: String?,
        medicineModel: MedicineModel?,
        style: Int = R.style.BaseAppTheme_TxtView_bold_medication,
    ): View {
        return TextView(ContextThemeWrapper(context, style)).apply {
            typeface = ResourcesCompat.getFont(context, R.font.nunito_bold)
            gravity = Gravity.CENTER_VERTICAL or Gravity.START
            drugName?.let { text = it }
            medicineModel?.dosage?.let {
                append("\n")
                append("$it ${context.getString(R.string.mg)}")
                append(",")
            }
            medicineModel?.noOfTablets?.let {
                append("\n")
                append("$it ${context.getString(R.string.dosage)}")
            }
            medicineModel?.noOfTime?.let {
                append(", ")
                append(it)
            }
            medicineModel?.perDayOrWeek?.let {
                append(" ")
                append(it)
            }
        }
    }

    private fun getNoneMedicineView(context: Context, drugName: String?): View {
        return getMedicineView(
            context,
            drugName,
            null,
            R.style.BaseAppTheme_TxtView_bold_medication_none
        )
    }

    private fun addMedicineView(flLayout: FlexboxLayout, medicineView: View) {
        val margin2dp = flLayout.context.resources.getDimension(R.dimen._2sdp).toInt()
        val layoutParams = FlexboxLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(margin2dp, 0, 0, margin2dp)
        flLayout.addView(medicineView, layoutParams)
    }

    private fun manageCategoryVisibilityAsPerEjectionFraction(
        diagnosisModel: DiagnosisModel,
        binding: FragmentDashboardBinding
    ) {
        if (diagnosisModel.isEjectionFractionEqualGreaterThan45()) {
            binding.includeChbMedications.tvBetaBlockerLabel.visibility = View.GONE
            binding.includeChbMedications.flBetaBlocker.visibility = View.GONE
            binding.includeChbMedications.tvArniLabel.visibility = View.GONE
            binding.includeChbMedications.flArni.visibility = View.GONE
            binding.includeChbMedications.tvAceLabel.visibility = View.GONE
            binding.includeChbMedications.flAce.visibility = View.GONE
        } else {
            binding.includeChbMedications.tvBetaBlockerLabel.visibility = View.VISIBLE
            binding.includeChbMedications.flBetaBlocker.visibility = View.VISIBLE
            binding.includeChbMedications.tvArniLabel.visibility = View.VISIBLE
            binding.includeChbMedications.flArni.visibility = View.VISIBLE
            binding.includeChbMedications.tvAceLabel.visibility = View.VISIBLE
            binding.includeChbMedications.flAce.visibility = View.VISIBLE
        }
    }

    private fun initCategories(binding: FragmentDashboardBinding) {
        betaBlocker =
            getContext(binding).resources.getString(R.string.beta_blocker).removeSpace()
        arni = getContext(binding).resources.getString(R.string.arni).removeSpace()
        sglt2_inhibitor =
            getContext(binding).resources.getString(R.string.sglt2_inhibitor).removeSpace()
        ace_arb = getContext(binding).resources.getString(R.string.ace_arb).removeSpace()
        mra = getContext(binding).resources.getString(R.string.mra).removeSpace()
        diuretic = getContext(binding).resources.getString(R.string.diuretic).removeSpace()
    }


}
package com.cardio.physician.ui.views.diagnosis.step2.dosageandfrequency

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.core.content.res.ResourcesCompat
import com.cardio.physician.R
import com.cardio.physician.databinding.ActivityDossageAndFrequencyBinding
import com.cardio.physician.domain.diagnosis.MedicineModel
import com.cardio.physician.ui.common.base.activity.BaseToolbarActivity
import com.cardio.physician.ui.common.customviews.toolbar.DefaultTitleWithBackButtonImp
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar
import com.cardio.physician.ui.common.utils.EXTRAS.EXTRAS_DOCTOR_ID

class DosageAndFrequencyActivity : BaseToolbarActivity() {

    private var medicineModel: MedicineModel? = null
    private val binding by viewBinding(ActivityDossageAndFrequencyBinding::inflate)

    //ActivityDossageAndFrequencyBinding
    companion object {
        fun getIntent(medicineModel: MedicineModel, activity: Activity): Intent {
            return Intent(activity, DosageAndFrequencyActivity::class.java).apply {
                putExtra(EXTRAS_DOCTOR_ID, medicineModel)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        medicineModel = intent.getParcelableExtra(EXTRAS_DOCTOR_ID)
        setListeners()
        setViews()
    }

    private fun setViews() {
        enableButtonClick(false)
        binding.cvDiagnosisBottomContainer.btNext.text = getString(R.string.button_add)
        showDosages(intent.getParcelableExtra(EXTRAS_DOCTOR_ID))
        var listNoOfTablets = resources.getStringArray(R.array.no_of_tablets).toList()
        var listTime = resources.getStringArray(R.array.no_of_time).toList()
        var listPerDay = resources.getStringArray(R.array.per_day).toList()
        binding.spinnerNoOfTablets.adapter = ArrayAdapter<String>(this,
            R.layout.item_medicine_search_layout,
            listNoOfTablets)
        binding.spinnerNoOfTime.adapter = ArrayAdapter<String>(this,
            R.layout.item_medicine_search_layout,
            listTime)
        binding.spinnerPerDay.adapter = ArrayAdapter<String>(this,
            R.layout.item_medicine_search_layout,
            listPerDay)
        //do below code after setting adapters
        medicineModel?.noOfTime?.let {
            binding.spinnerNoOfTime.setSelection(listTime
                .indexOf(it))
        }
        medicineModel?.noOfTablets?.let {
            binding.spinnerNoOfTablets.setSelection(listNoOfTablets
                .indexOf(it))
        }
        medicineModel?.perDayOrWeek?.let {
            binding.spinnerPerDay.setSelection(listPerDay
                .indexOf(it))
        }
        medicineModel?.dosage?.let { dosage ->
            medicineModel?.listDosage?.indexOf(dosage)?.let {
                if (binding.rgDosageOption.childCount > it)
                    (binding.rgDosageOption.getChildAt(it) as RadioButton).isChecked = true
            }
        }
    }

    private fun showDosages(medicineModel: MedicineModel?) {
        medicineModel?.let {
            it.listDosage?.forEach {
                binding.rgDosageOption.addView(getRadioButton(it))
            }
        }
    }

    private fun getRadioButton(dosage: String): View? = RadioButton(this).apply {
        tag=dosage
        text = "$dosage ${getString(R.string.mg)} ${getString(R.string.tablet)}"
        typeface = ResourcesCompat.getFont(context, R.font.nunito_regular)
    }

    private fun setListeners() {
        binding.rgDosageOption.setOnCheckedChangeListener { radioGroup, checkedId ->
            //use tag as we do not have to send "tablet" text with "mg"
            (radioGroup.findViewById<RadioButton>(checkedId).tag as? String)?.let {
                medicineModel?.dosage = it
                setAlphaVisiblity()
            }
        }
        binding.cvDiagnosisBottomContainer.btNext.setOnClickListener {
            medicineModel?.noOfTime = binding.spinnerNoOfTime.selectedItem.toString()
            medicineModel?.perDayOrWeek = binding.spinnerPerDay.selectedItem.toString()
            medicineModel?.noOfTablets = binding.spinnerNoOfTablets.selectedItem.toString()
            setResult(RESULT_OK, Intent().apply {
                putExtra(EXTRAS_DOCTOR_ID, medicineModel)
            })
            finish()
        }
        binding.cvDiagnosisBottomContainer.btCancel.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

    private fun setAlphaVisiblity() {
        enableButtonClick(allValidated())
    }

    private fun allValidated(): Boolean {
        return binding.rgDosageOption.checkedRadioButtonId != -1
    }

    private fun enableButtonClick(isEnable: Boolean) {
        if (isEnable) {
            binding.cvDiagnosisBottomContainer.btNext.isEnabled = true
            binding.cvDiagnosisBottomContainer.btNext.alpha = 1.0f
        } else {
            binding.cvDiagnosisBottomContainer.btNext.isEnabled = false
            binding.cvDiagnosisBottomContainer.btNext.alpha = 0.3f
        }
    }

    override fun getToolbarImp(): IToolbar =
        DefaultTitleWithBackButtonImp(binding.headerView.toolBarContainer,
            R.string.title_dossage_nd_frequency, {
                onBackPressed()
            },true)
}
package com.cardio.doctor.ui.views.diagnosis.step1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cardio.doctor.R
import com.cardio.doctor.databinding.FragmentDiagnosisPart1Binding
import com.cardio.doctor.domain.common.model.validation.ValidationModelV2
import com.cardio.doctor.network.Status
import com.cardio.doctor.ui.common.utils.extentions.getTrimmedText
import com.cardio.doctor.ui.common.utils.textwatcher.LabelVisiblityHelper
import com.cardio.doctor.ui.common.utils.validation.FieldType
import com.cardio.doctor.ui.views.diagnosis.common.BaseDiagnosisFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DiagnosisFragmentStep1 : BaseDiagnosisFragment<FragmentDiagnosisPart1Binding>() {

    private val viewModel: DiagnosisViewStep1ViewModel by viewModels()
    @Inject
    lateinit var labelVisiblityHelper: LabelVisiblityHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiagnosisPart1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.cvDiagnosisBottomContainer.btNext.setOnClickListener {
            onSubmitClick()
        }
        binding.cvDiagnosisBottomContainer.btCancel.setOnClickListener {
            parentActivity?.onBackPressed()
        }
        labelVisiblityHelper.addView(binding.clPatientDetail.edtFirstName,binding.clPatientDetail.tvFirstNameError,binding.clPatientDetail.firstNameLabel)
        labelVisiblityHelper.addView(binding.clPatientDetail.edtLastName,binding.clPatientDetail.tvErrorLastName,binding.clPatientDetail.txtLastName)
        labelVisiblityHelper.addView(binding.clPatientDetail.edtAge,binding.clPatientDetail.tvAgeError,binding.clPatientDetail.txtAge)
        labelVisiblityHelper.addView(binding.clHealthDetail.edtWeight,binding.clHealthDetail.tvWeightError,binding.clHealthDetail.tvWeight)
        labelVisiblityHelper.addView(binding.clHealthDetail.edtHeartRate,binding.clHealthDetail.tvErrorHeartRate,binding.clHealthDetail.txtHeartRate)
        labelVisiblityHelper.addView(binding.clHealthDetail.edtTopBp,binding.clHealthDetail.tvTopBpErrro,binding.clHealthDetail.txtTopBp)
        labelVisiblityHelper.addView(binding.clHealthDetail.edtBottomBp,binding.clHealthDetail.tvBottomBpErrror,binding.clHealthDetail.txtBottomBp)
    }

    private fun onSubmitClick() {
        var firstName = binding.clPatientDetail.edtFirstName.getTrimmedText()
        var lastName = binding.clPatientDetail.edtLastName.getTrimmedText()
        var age = binding.clPatientDetail.edtAge.getTrimmedText()
        var weight = binding.clHealthDetail.edtWeight.getTrimmedText()
        var heartRate = binding.clHealthDetail.edtHeartRate.getTrimmedText()
        var topBp = binding.clHealthDetail.edtTopBp.getTrimmedText()
        var bottomBp = binding.clHealthDetail.edtBottomBp.getTrimmedText()
        viewModel.checkValidation(firstName, lastName, age, weight, heartRate, topBp, bottomBp, {
            findNavController().navigate(DiagnosisFragmentStep1Directions.actionDiagnosisFragmentPart1ToDiagnosisFragmentPart2())
        }, {
            onValidationsFailed(it)
        })
    }

    private fun onValidationsFailed(it: List<ValidationModelV2>) {
        it.forEach {
            when (it.status) {
                Status.SUCCESS -> {
                    setValidationOnViews(
                        it.field_type,
                        R.drawable.edt_rounded_corner,
                        View.GONE,
                        it.message
                    )
                }
                Status.ERROR -> {
                    setValidationOnViews(
                        it.field_type,
                        R.drawable.edt_rounded_corner_red,
                        View.VISIBLE,
                        it.message
                    )
                }
            }
        }
    }

    private fun setValidationOnViews(
        fieldType: FieldType,
        edtRoundedCorner: Int,
        visibility: Int,
        message: String
    ) {
        var editText: EditText? = null
        var txtError: TextView? = null
        when (fieldType) {
            FieldType.FIRST_NAME -> {
                editText = binding.clPatientDetail.edtFirstName
                txtError = binding.clPatientDetail.tvFirstNameError
            }
            FieldType.LAST_NAME -> {
                editText = binding.clPatientDetail.edtLastName
                txtError = binding.clPatientDetail.tvErrorLastName
            }
            FieldType.AGE -> {
                editText = binding.clPatientDetail.edtAge
                txtError = binding.clPatientDetail.tvAgeError
            }
            FieldType.WEIGHT -> {
                editText = binding.clHealthDetail.edtWeight
                txtError = binding.clHealthDetail.tvWeightError
            }
            FieldType.HEART_RATE -> {
                editText = binding.clHealthDetail.edtHeartRate
                txtError = binding.clHealthDetail.tvErrorHeartRate
            }
            FieldType.TOP_BP -> {
                editText = binding.clHealthDetail.edtTopBp
                txtError = binding.clHealthDetail.tvTopBpErrro
            }
            FieldType.BOTTOM_BP -> {
                editText = binding.clHealthDetail.edtBottomBp
                txtError = binding.clHealthDetail.tvBottomBpErrror
            }
        }
        editText?.run {
            setBackgroundResource(edtRoundedCorner)
        }
        txtError?.run {
            txtError.text = message
            setVisibility(visibility)
        }
    }

}
package com.cardio.doctor.ui.views.diagnosis.step1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cardio.doctor.R
import com.cardio.doctor.databinding.FragmentDiagnosisPart1Binding
import com.cardio.doctor.domain.common.model.validation.ValidationModelV2
import com.cardio.doctor.network.Status
import com.cardio.doctor.ui.common.utils.extentions.getTrimmedText
import com.cardio.doctor.ui.common.utils.validation.FieldType
import com.cardio.doctor.ui.views.diagnosis.common.BaseDiagnosisFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiagnosisFragmentStep1 : BaseDiagnosisFragment<FragmentDiagnosisPart1Binding>() {

    private val viewModel: DiagnosisViewStep1ViewModel by viewModels()

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
    }

    private fun onSubmitClick() {
        var firstName = binding.clPatientDetail.edtFirstName.getTrimmedText()
        var lastName = binding.clPatientDetail.edtLastName.getTrimmedText()
        var age = binding.clPatientDetail.edtAge.getTrimmedText()
        var weight = binding.clHealthDetail.edtWeight.getTrimmedText()
        var heartRate = binding.clHealthDetail.edtHeartRate.getTrimmedText()
        var topBp = binding.clHealthDetail.edtTopBp.getTrimmedText()
        var bottomBp = binding.clHealthDetail.edtBottomBp.getTrimmedText()
        viewModel.checkValidation(
            firstName,
            lastName,
            age,
            weight,
            heartRate,
            topBp,
            bottomBp,
            {
                findNavController().navigate(DiagnosisFragmentStep1Directions.actionDiagnosisFragmentPart1ToDiagnosisFragmentPart2())
            },
            {
                onValidationsFailed(it)
            })


    }

    private fun onValidationsFailed(it: List<ValidationModelV2>) {
        it.forEach {
            when (it.status) {
                Status.SUCCESS -> {
                    setValidationOnViews(it.field_type,R.drawable.edt_rounded_corner,View.GONE)
                }
                Status.ERROR -> {
                    setValidationOnViews(it.field_type,R.drawable.edt_rounded_corner_red,View.VISIBLE)
                }
            }
        }
    }

    private fun setValidationOnViews(fieldType: FieldType, edtRoundedCorner: Int, visibility: Int) {
        when (fieldType) {
            FieldType.FIRST_NAME -> {
                binding.clPatientDetail.firstNameLabel.setBackgroundResource(edtRoundedCorner)
                binding.clPatientDetail.tvFirstNameError.visibility = visibility
            }
            FieldType.LAST_NAME -> {
                binding.clPatientDetail.edtLastName.setBackgroundResource(edtRoundedCorner)
                binding.clPatientDetail.tvErrorLastName.visibility = visibility
            }
            FieldType.AGE -> {
                binding.clPatientDetail.edtAge.setBackgroundResource(edtRoundedCorner)
                binding.clPatientDetail.tvAgeError.visibility = visibility
            }
            FieldType.WEIGHT -> {
                binding.clHealthDetail.edtWeight.setBackgroundResource(edtRoundedCorner)
                binding.clHealthDetail.tvWeight.visibility = visibility
            }
            FieldType.HEART_RATE -> {
                binding.clHealthDetail.edtHeartRate.setBackgroundResource(edtRoundedCorner)
                binding.clHealthDetail.tvErrorHeartRate.visibility = visibility
            }
            FieldType.TOP_BP -> {
                binding.clHealthDetail.edtTopBp.setBackgroundResource(edtRoundedCorner)
                binding.clHealthDetail.tvTopBpErrro.visibility = visibility
            }
            FieldType.BOTTOM_BP -> {
                binding.clHealthDetail.edtBottomBp.setBackgroundResource(edtRoundedCorner)
                binding.clHealthDetail.tvBottomBpErrror.visibility = visibility
            }
        }
    }

}
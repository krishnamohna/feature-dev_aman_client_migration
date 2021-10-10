package com.cardio.doctor.ui.views.diagnosis.step1

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cardio.doctor.R
import com.cardio.doctor.databinding.FragmentDiagnosisPart1Binding
import com.cardio.doctor.domain.common.model.UserModel
import com.cardio.doctor.domain.common.model.validation.ValidationModelV2
import com.cardio.doctor.network.Status
import com.cardio.doctor.ui.common.utils.extentions.customObserver
import com.cardio.doctor.ui.common.utils.extentions.getTrimmedText
import com.cardio.doctor.ui.common.utils.getNoYearsFromDate
import com.cardio.doctor.ui.common.utils.keyboard.KeyboardEventListener
import com.cardio.doctor.ui.common.utils.showConfirmAlertDialog
import com.cardio.doctor.ui.common.utils.textwatcher.DELAY_SMALL_ANIMATION
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
        setViews()
        setDataInViewIfExist()
        setObservers()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        launchWithMinDelay { viewModel.getUserProfile() }
    }

    override fun onResume() {
        super.onResume()
        KeyboardEventListener(R.id.clScrollViewContainer,parentActivity!!) {
            if (it) {
                var labelHeight=resources.getDimension(R.dimen.offset_scroll_label_up_medium).toInt()
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.scrollViewStep1.smoothScrollBy(0, labelHeight)
                }, DELAY_SMALL_ANIMATION)
            }
        }
    }

    private fun setObservers() {
        viewModel.userDetailDocument.customObserver(
            viewLifecycleOwner,
            onLoading = {
                showProgress(it)
            },
            onSuccess = {
                setPatientDetail(it)
            },
            onError = {}
        )
    }

    private fun setPatientDetail(userModel: UserModel?) {
        userModel?.run {
            firstName?.let { binding.clPatientDetail.edtFirstName.setText(it) }
            lastName?.let { binding.clPatientDetail.edtLastName.setText(it) }
            weight?.let { binding.clHealthDetail.edtWeight.setText(it) }
            dob?.isNotEmpty()?.also {
                if(it)
                binding.clPatientDetail.edtAge.setText(dob.getNoYearsFromDate().toString())
            }
        }
    }

    private fun setViews() {
        setStepView(binding.stepView.stepView)
    }


    private fun setDataInViewIfExist() {
        diagnosisActivity?.getDiagnosisModel()?.firstName?.run {
            binding.clPatientDetail.edtFirstName.setText(
                this
            )
        }
        diagnosisActivity?.getDiagnosisModel()?.lastName?.run {
            binding.clPatientDetail.edtLastName.setText(
                this
            )
        }
        diagnosisActivity?.getDiagnosisModel()?.age?.run {
            binding.clPatientDetail.edtAge.setText(
                this
            )
        }
        diagnosisActivity?.getDiagnosisModel()?.weight?.run {
            binding.clHealthDetail.edtWeight.setText(
                this
            )
        }
        diagnosisActivity?.getDiagnosisModel()?.heartRate?.run {
            binding.clHealthDetail.edtHeartRate.setText(
                this
            )
        }
        diagnosisActivity?.getDiagnosisModel()?.topBp?.run {
            binding.clHealthDetail.edtTopBp.setText(
                this
            )
        }
        diagnosisActivity?.getDiagnosisModel()?.bottomBp?.run {
            binding.clHealthDetail.edtBottomBp.setText(
                this
            )
        }
    }

    private fun setListeners() {
        binding.cvDiagnosisBottomContainer.btNext.setOnClickListener {
            onSubmitClick()
        }
        binding.cvDiagnosisBottomContainer.btCancel.setOnClickListener {
            showConfirmAlertDialog(
                parentActivity!!,
                getString(R.string.confirm),
                getString(R.string.confirm_dismiss_diagnosis)
            ) { btnText: String, dialog: DialogInterface ->
                when (btnText) {
                    getString(R.string.yes) -> {
                        parentActivity?.finish()
                    }
                }
            }
        }
        labelVisiblityHelper.addView(
            binding.clPatientDetail.edtFirstName,
            binding.clPatientDetail.tvFirstNameError,
            binding.clPatientDetail.firstNameLabel,
            binding.scrollViewStep1,parentActivity
        )
        labelVisiblityHelper.addView(
            binding.clPatientDetail.edtLastName,
            binding.clPatientDetail.tvErrorLastName,
            binding.clPatientDetail.txtLastName,
            binding.scrollViewStep1,
            parentActivity
        )
        labelVisiblityHelper.addView(
            binding.clPatientDetail.edtAge,
            binding.clPatientDetail.tvAgeError,
            binding.clPatientDetail.txtAge,
            binding.scrollViewStep1,
            parentActivity
        )
        labelVisiblityHelper.addView(
            binding.clHealthDetail.edtWeight,
            binding.clHealthDetail.tvWeightError,
            binding.clHealthDetail.tvWeight,
            binding.scrollViewStep1,
            parentActivity
        )
        labelVisiblityHelper.addView(
            binding.clHealthDetail.edtHeartRate,
            binding.clHealthDetail.tvErrorHeartRate,
            binding.clHealthDetail.txtHeartRate,
            binding.scrollViewStep1,
            parentActivity
        )
        labelVisiblityHelper.addView(
            binding.clHealthDetail.edtTopBp,
            binding.clHealthDetail.tvTopBpErrro,
            binding.clHealthDetail.txtTopBp,
            binding.scrollViewStep1,
            parentActivity
        )
        labelVisiblityHelper.addView(
            binding.clHealthDetail.edtBottomBp,
            binding.clHealthDetail.tvBottomBpErrror,
            binding.clHealthDetail.txtBottomBp,
            binding.scrollViewStep1,
            parentActivity
        )
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
            saveStateToParent(firstName, lastName, age, weight, heartRate, topBp, bottomBp)
            findNavController().navigate(DiagnosisFragmentStep1Directions.actionDiagnosisFragmentPart1ToDiagnosisFragmentPart2())
        }, {
            onValidationsFailed(it)
        })
    }

    private fun saveStateToParent(
        firstName: String,
        lastName: String,
        age: String,
        weight: String,
        heartRate: String,
        topBp: String,
        bottomBp: String
    ) {
        diagnosisActivity?.getDiagnosisModel()?.firstName = firstName
        diagnosisActivity?.getDiagnosisModel()?.lastName = lastName
        diagnosisActivity?.getDiagnosisModel()?.age = age
        diagnosisActivity?.getDiagnosisModel()?.weight = weight as? Int
        diagnosisActivity?.getDiagnosisModel()?.heartRate = heartRate as? Int
        diagnosisActivity?.getDiagnosisModel()?.topBp = topBp as? Int
        diagnosisActivity?.getDiagnosisModel()?.bottomBp = bottomBp as? Int
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
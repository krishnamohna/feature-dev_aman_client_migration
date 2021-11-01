package com.cardio.doctor.ui.views.diagnosis.step1

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cardio.doctor.R
import com.cardio.doctor.databinding.FragmentDiagnosisPart1Binding
import com.cardio.doctor.domain.common.model.UserModel
import com.cardio.doctor.domain.common.model.validation.ValidationModelV2
import com.cardio.doctor.domain.fitness.model.FitnessModel
import com.cardio.doctor.domain.fitness.model.HeartRateModel
import com.cardio.doctor.network.Status
import com.cardio.doctor.network.api.EXTRAS
import com.cardio.doctor.ui.common.utils.convertMetricWeightToPound
import com.cardio.doctor.ui.common.utils.extentions.customObserver
import com.cardio.doctor.ui.common.utils.extentions.getTrimmedText
import com.cardio.doctor.ui.common.utils.getNoYearsFromDate
import com.cardio.doctor.ui.common.utils.keyboard.KeyboardEventListener
import com.cardio.doctor.ui.common.utils.showConfirmAlertDialog
import com.cardio.doctor.ui.common.utils.showToast
import com.cardio.doctor.ui.common.utils.textwatcher.DELAY_SMALL_ANIMATION
import com.cardio.doctor.ui.common.utils.textwatcher.LabelVisiblityHelper
import com.cardio.doctor.ui.common.utils.validation.FieldType
import com.cardio.doctor.ui.views.diagnosis.common.BaseDiagnosisFragment
import com.cardio.doctor.ui.views.diagnosis.step1.adapter.AilmentDropDownAdapter
import com.cardio.doctor.ui.views.sync_health_data.activity.SyncHealthActivty
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DiagnosisFragmentStep1 : BaseDiagnosisFragment<FragmentDiagnosisPart1Binding>() {

    private var isKeyBoardOpen: Boolean = false
    private val viewModel: DiagnosisViewStep1ViewModel by viewModels()

    @Inject
    lateinit var labelVisiblityHelper: LabelVisiblityHelper

    private var resultLauncherSpeechToText: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result?.data?.getParcelableExtra<FitnessModel>(EXTRAS.USER_PROFILE)
                    ?.let { setPatientDetail(it) }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDiagnosisPart1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setViews()
        setObservers()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        launchWithMinDelay { viewModel.getUserProfile() }
    }

    override fun onResume() {
        super.onResume()
        KeyboardEventListener(R.id.clScrollViewContainer, parentActivity!!) {
            isKeyBoardOpen = it
            if (it) {
                val labelHeight =
                    resources.getDimension(R.dimen.offset_scroll_label_up_medium).toInt()
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.scrollViewStep1.smoothScrollBy(0, labelHeight)
                }, DELAY_SMALL_ANIMATION)
            }
        }
    }

    private fun setObservers() {
        viewModel.userDetailDocument.customObserver(viewLifecycleOwner, onLoading = {
            showProgress(it)
        }, onSuccess = {
            setPatientDetail(it)
        }, onError = { msg ->
            parentActivity?.let { showToast(it, msg ?: getString(R.string.getting_some_error)) }
        })
        viewModel.getUserFitnessData()
            .customObserver(this, onLoading = ::showProgress, onSuccess = {
                it?.let {
                    setPatientDetail(it)
                }
            }, onError = ::onError)
        viewModel.getHeartRateLiveData()
            .customObserver(this, onLoading = ::showProgress, onSuccess = {
                it?.let {
                    setPatientDetail(it)
                }
            }, onError = ::onError)
    }

    private fun setPatientDetail(it: HeartRateModel) {
        it.restHeartRate?.let { binding.clHealthDetail.edtHeartRate.setText(it.toString()) }
    }

    private fun setPatientDetail(userModel: FitnessModel) {
        binding.clHealthDetail.edtWeight.setText(userModel.weight?.toString()
            ?.convertMetricWeightToPound(userModel.weightUnit))
        binding.clHealthDetail.edtHeartRate.setText(userModel.heartRate?.toString())
    }

    private fun setPatientDetail(userModel: UserModel?) {
        userModel?.run {
            firstName?.let { binding.clPatientDetail.edtFirstName.setText(it) }
            lastName?.let { binding.clPatientDetail.edtLastName.setText(it) }
            weight?.let { binding.clHealthDetail.edtWeight.setText(it) }
            dob?.isNotEmpty()?.also {
                if (it)
                    binding.clPatientDetail.edtAge.setText(dob.getNoYearsFromDate().toString())
            }
        }
    }

    private fun setViews() {
        binding.spinnerCategory.adapter = AilmentDropDownAdapter(
            parentActivity!!,
            R.layout.item_dropdown_ailment,
            resources.getStringArray(R.array.array_ailment)
        )
    }

    private fun setListeners() {
        diagnosisActivity?.onConnectClick {
            Intent(
                requireContext(),
                SyncHealthActivty::class.java
            ).run { resultLauncherSpeechToText.launch(this) }
        }
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
            binding.scrollViewStep1, parentActivity
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
        isAllValidationSuccessFull({
            findNavController().navigate(DiagnosisFragmentStep1Directions.actionDiagnosisFragmentPart1ToDiagnosisFragmentPart2())
        }, {
            onValidationsFailed(it)
        })
    }

    private fun isAllValidationSuccessFull(
        succcess: () -> Unit,
        failed: (validations: List<ValidationModelV2>) -> Unit,
    ) {
        val firstName = binding.clPatientDetail.edtFirstName.getTrimmedText()
        val lastName = binding.clPatientDetail.edtLastName.getTrimmedText()
        val age = binding.clPatientDetail.edtAge.getTrimmedText()
        val weight = binding.clHealthDetail.edtWeight.getTrimmedText()
        val heartRate = binding.clHealthDetail.edtHeartRate.getTrimmedText()
        val topBp = binding.clHealthDetail.edtTopBp.getTrimmedText()
        val bottomBp = binding.clHealthDetail.edtBottomBp.getTrimmedText()
        val ailment = binding.spinnerCategory.selectedItemPosition
        viewModel.checkValidation(
            ailment,
            firstName,
            lastName,
            age,
            weight,
            heartRate,
            topBp,
            bottomBp,
            {
                saveStateToParent(firstName, lastName, age, weight, heartRate, topBp, bottomBp)
                succcess.invoke()
            },
            failed)
    }

    private fun saveStateToParent(
        firstName: String,
        lastName: String,
        age: String,
        weight: String,
        heartRate: String,
        topBp: String,
        bottomBp: String,
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
        message: String,
    ) {
        var editText: EditText? = null
        var txtError: TextView? = null
        when (fieldType) {
            FieldType.AILMENT -> {
                if (isKeyBoardOpen && message.isNotEmpty())
                    showToast(parentActivity!!, message)
                txtError = binding.tvAilmentError
            }
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
            setText(message)
            setVisibility(visibility)
        }
    }

}
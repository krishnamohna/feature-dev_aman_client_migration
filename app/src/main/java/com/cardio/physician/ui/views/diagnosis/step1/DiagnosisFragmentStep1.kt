package com.cardio.physician.ui.views.diagnosis.step1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cardio.physician.R
import com.cardio.physician.databinding.FragmentDiagnosisPart1Binding
import com.cardio.physician.domain.common.model.UserModel
import com.cardio.physician.domain.common.model.validation.ValidationModelV2
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.domain.fitness.model.HeartRateModel
import com.cardio.physician.network.Status
import com.cardio.physician.ui.common.utils.*
import com.cardio.physician.ui.common.utils.DateFormat_.DATE_FORMAT_DD_MMM_YYYY
import com.cardio.physician.ui.common.utils.DateFormat_.DATE_FORMAT_DD_MM_YYYY_DATE_PICKER
import com.cardio.physician.ui.common.utils.EXTRAS.USER_PROFILE
import com.cardio.physician.ui.common.utils.extentions.customObserver
import com.cardio.physician.ui.common.utils.extentions.getTrimmedText
import com.cardio.physician.ui.common.utils.inputfilter.DecimalDigitsInputFilter
import com.cardio.physician.ui.common.utils.keyboard.KeyboardEventListener
import com.cardio.physician.ui.common.utils.textwatcher.DELAY_SMALL_ANIMATION
import com.cardio.physician.ui.common.utils.textwatcher.LabelVisiblityHelper
import com.cardio.physician.ui.common.utils.validation.FieldType
import com.cardio.physician.ui.views.diagnosis.common.BaseDiagnosisFragment
import com.cardio.physician.ui.views.diagnosis.step1.adapter.AilmentDropDownAdapter
import com.cardio.physician.ui.views.sync_health_data.activity.SyncHealthActivty
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
open class DiagnosisFragmentStep1 : BaseDiagnosisFragment<FragmentDiagnosisPart1Binding>() {

    var birthDate: Date? = null
    private var isKeyBoardOpen: Boolean = false
    val viewModel: DiagnosisViewStep1ViewModel by viewModels()

    @Inject
    lateinit var labelVisiblityHelper: LabelVisiblityHelper

    private var resultLauncherFitnessData: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result?.data?.getParcelableExtra<FitnessModel>(USER_PROFILE)
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
        setInputFilters()
        init()
        //just testing commit in new studio
    }

    private fun init() {
        loadUserProfile()
    }

    open fun loadUserProfile() {
        // check if user is returning from next screen
        if (diagnosisActivity!!.diagnosisModel.firstName.isNullOrBlank())
            launchWithMinDelay { viewModel.getUserProfile(args.userId) }
    }

    private fun setInputFilters() {
        binding.clHealthDetail.edtWeight.filters =
            arrayOf<InputFilter>(DecimalDigitsInputFilter(Constants.WEIGHT_DIGITS_BEFORE_ZERO, 2))
        binding.clHealthDetail.edtBottomBp.filters =
            arrayOf<InputFilter>(DecimalDigitsInputFilter(Constants.HEART_DIGITS_BEFORE_ZERO, 2))
        binding.clHealthDetail.edtTopBp.filters =
            arrayOf<InputFilter>(DecimalDigitsInputFilter(Constants.TOP_BP_DIGITS_BEFORE_ZERO, 2))
        binding.clHealthDetail.edtHeartRate.filters =
            arrayOf<InputFilter>(DecimalDigitsInputFilter(Constants.BOTTOM_BP_DIGITS_BEFORE_ZERO, 2))
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

    open fun setObservers() {
        viewModel.userDetailDocument.customObserver(viewLifecycleOwner, onLoading = {
            showProgress(it)
        }, onSuccess = {
            setPatientDetail(it)
        }, onError = { msg, exp ->
            parentActivity?.let { showToast(it, msg ?: getString(R.string.getting_some_error)) }
        })
    }

    private fun setPatientDetail(it: HeartRateModel) {
        it.restHeartRate?.let { binding.clHealthDetail.edtHeartRate.setText(it.toString()) }
    }

    private fun setPatientDetail(fitnessModel: FitnessModel) {
        fitnessModel.weight?.let {
            binding.clHealthDetail.edtWeight.setText(it
                ?.convertMetricWeightToPound(fitnessModel.weightUnit))
        }
        fitnessModel.heartRate?.let { binding.clHealthDetail.edtHeartRate.setText(it) }
        fitnessModel.bloodPressureTopBp?.let { binding.clHealthDetail.edtTopBp.setText(it) }
        fitnessModel.bloodPressureBottomBp?.let { binding.clHealthDetail.edtBottomBp.setText(it) }
        fitnessModel.stepCount?.let { binding.clHealthDetail.edtStepCount.setText(it) }
    }

    private fun setPatientDetail(userModel: UserModel?) {
        userModel?.run {
            firstName?.let { binding.clPatientDetail.edtFirstName.setText(it) }
            lastName?.let { binding.clPatientDetail.edtLastName.setText(it) }
            weight?.let { binding.clHealthDetail.edtWeight.setText(it) }
            dob?.isNotEmpty()?.also {
                if (it) {
                    birthDate = dob.datePickerStringToDate(DATE_FORMAT_DD_MMM_YYYY)
                    binding.clPatientDetail.edtAge.setText(dob/*dob.getNoYearsFromDate().toString()*/)
                }
            }
        }
    }

    open fun setViews() {
        binding.spinnerCategory.adapter = AilmentDropDownAdapter(
            parentActivity!!,
            R.layout.item_dropdown_ailment,
            resources.getStringArray(R.array.array_ailment)
        )
        setStepView(binding.stepView.stepView)
    }

    private fun setListeners() {
        setViewListener()
        setLabelVisibilityListener()
        setAlphaVisibilityListener()
    }

    private fun setViewListener() {
        binding.spinnerCategory?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    onFieldTextChange()
                }
            }
        binding.clPatientDetail.edtAge.setOnClickListener { view ->
            getBirthDatePicker(requireContext(), birthDate) { _, year, month, date ->
                val dateString = getDate(date).plus("-")
                    .plus(getMonthNumber(month).toString().plus("-"))
                    .plus(year)
                birthDate = dateString.datePickerStringToDate(DATE_FORMAT_DD_MM_YYYY_DATE_PICKER)
                getStringFromDate(birthDate)?.let { date ->
                    binding.clPatientDetail.edtAge.setText(date/*date.getNoYearsFromDate().toString()*/)
                }
            }?.show()
        }
        diagnosisActivity?.onConnectClick {
            Intent(
                requireContext(),
                SyncHealthActivty::class.java
            ).run { resultLauncherFitnessData.launch(this) }
        }
        binding.cvDiagnosisBottomContainer.btNext.setOnClickListener {
            onSubmitClick()
        }
        binding.cvDiagnosisBottomContainer.btCancel.setOnClickListener {
            diagnosisActivity?.onBackPressed()
            /*showConfirmAlertDialog(
                parentActivity!!,
                getString(R.string.confirm),
                getString(R.string.confirm_dismiss_diagnosis)
            ) { btnText: String, dialog: DialogInterface ->
                when (btnText) {
                    getString(R.string.yes) -> {
                        parentActivity?.finish()
                    }
                }
            }*/
        }
    }

    private fun setAlphaVisibilityListener() {
        var listEdtFields = listOf<EditText>(
            binding.clPatientDetail.edtFirstName,
            binding.clPatientDetail.edtLastName,
            binding.clPatientDetail.edtAge
        )
        viewModel.setAlphaValidationListener(listEdtFields) {
            onFieldTextChange()
        }
    }

    private fun onFieldTextChange() {
        isAllAlphaValidationSuccessFull({
            enableButtonClick(true)
        }, {
            enableButtonClick(false)
        })
    }

    private fun setLabelVisibilityListener() {
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
        labelVisiblityHelper.addView(
            binding.clHealthDetail.edtStepCount,
            binding.clHealthDetail.tvStepCountError,
            binding.clHealthDetail.textStepCount,
            binding.scrollViewStep1,
            parentActivity
        )
    }

    fun onSubmitClick() {
        isAllValidationSuccessFull({
            onNextClickAfterValidation()
        }, {
            onValidationsFailed(it)
        },{error->
            showToast(requireContext(),error)
        })
    }

    val args: DiagnosisFragmentStep1Args by navArgs()

    open fun onNextClickAfterValidation() {
        findNavController().navigate(DiagnosisFragmentStep1Directions.actionDiagnosisFragmentPart1ToDiagnosisFragmentPart2(args.userId))
    }

    private fun isAllValidationSuccessFull(
        success: () -> Unit,
        failed: (validations: List<ValidationModelV2>) -> Unit,
        failed2: (error: String) -> Unit,
    ) {
        val firstName = binding.clPatientDetail.edtFirstName.getTrimmedText()
        val lastName = binding.clPatientDetail.edtLastName.getTrimmedText()
        val age = binding.clPatientDetail.edtAge.getTrimmedText()
        val weight = binding.clHealthDetail.edtWeight.getTrimmedText()
        val heartRate = binding.clHealthDetail.edtHeartRate.getTrimmedText()
        val topBp = binding.clHealthDetail.edtTopBp.getTrimmedText()
        val bottomBp = binding.clHealthDetail.edtBottomBp.getTrimmedText()
        val stepCount = binding.clHealthDetail.edtStepCount.getTrimmedText()
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
            stepCount,
            {
                saveStateToParent(binding.spinnerCategory.selectedItem.toString(),
                    firstName,
                    lastName,
                    age,
                    weight,
                    heartRate,
                    topBp,
                    bottomBp, stepCount)
                success.invoke()
            },
            failed,failed2)
    }

    private fun isAllAlphaValidationSuccessFull(
        success: () -> Unit,
        failed: (validations: List<ValidationModelV2>) -> Unit,
    ) {
        val firstName = binding.clPatientDetail.edtFirstName.getTrimmedText()
        val lastName = binding.clPatientDetail.edtLastName.getTrimmedText()
        val age = binding.clPatientDetail.edtAge.getTrimmedText()
        val weight = binding.clHealthDetail.edtWeight.getTrimmedText()
        val heartRate = binding.clHealthDetail.edtHeartRate.getTrimmedText()
        val topBp = binding.clHealthDetail.edtTopBp.getTrimmedText()
        val bottomBp = binding.clHealthDetail.edtBottomBp.getTrimmedText()
        val steps = binding.clHealthDetail.edtStepCount.getTrimmedText()
        val ailment = binding.spinnerCategory.selectedItemPosition
        viewModel.checkAlphaValidation(
            ailment,
            firstName,
            lastName,
            age,
            weight,
            heartRate,
            topBp,
            bottomBp,
            {
                saveStateToParent(binding.spinnerCategory.selectedItem.toString(),
                    firstName,
                    lastName,
                    age,
                    weight,
                    heartRate,
                    topBp,
                    bottomBp,steps)
                success.invoke()
            },
            failed)
    }

    private fun saveStateToParent(
        ailment: String,
        firstName: String,
        lastName: String,
        age: String,
        weight: String,
        heartRate: String,
        topBp: String,
        bottomBp: String,
        steps: String
    ) {
        diagnosisActivity?.diagnosisModel?.firstName = firstName
        diagnosisActivity?.diagnosisModel?.lastName = lastName
        diagnosisActivity?.diagnosisModel?.age = age
        diagnosisActivity?.diagnosisModel?.weight = weight
        diagnosisActivity?.diagnosisModel?.heartRate = heartRate
        diagnosisActivity?.diagnosisModel?.topBp = topBp
        diagnosisActivity?.diagnosisModel?.bottomBp = bottomBp
        diagnosisActivity?.diagnosisModel?.stepCount = steps
        diagnosisActivity?.diagnosisModel?.ailment = ailment
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

    private fun enableButtonClick(isEnable: Boolean) {
        if (isEnable) {
            binding.cvDiagnosisBottomContainer.btNext.isEnabled = true
            binding.cvDiagnosisBottomContainer.btNext.alpha = 1.0f
        } else {
            binding.cvDiagnosisBottomContainer.btNext.isEnabled = false
            binding.cvDiagnosisBottomContainer.btNext.alpha = 0.3f
        }
    }

}
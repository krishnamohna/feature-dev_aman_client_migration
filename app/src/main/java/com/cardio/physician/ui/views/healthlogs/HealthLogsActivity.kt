package com.cardio.physician.ui.views.healthlogs

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import com.cardio.physician.R
import com.cardio.physician.databinding.ActivityHealthLogsBinding
import com.cardio.physician.domain.common.model.validation.ValidationModelV2
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.network.Status
import com.cardio.physician.ui.common.base.activity.BaseToolbarActivity
import com.cardio.physician.ui.common.customviews.toolbar.HealthLogsToolbarImp
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar
import com.cardio.physician.ui.common.utils.*
import com.cardio.physician.ui.common.utils.Constants.BOTTOM_BP_DIGITS_BEFORE_ZERO
import com.cardio.physician.ui.common.utils.Constants.HEART_DIGITS_BEFORE_ZERO
import com.cardio.physician.ui.common.utils.Constants.TOP_BP_DIGITS_BEFORE_ZERO
import com.cardio.physician.ui.common.utils.Constants.WEIGHT_DIGITS_BEFORE_ZERO
import com.cardio.physician.ui.common.utils.extentions.clearHoursMins
import com.cardio.physician.ui.common.utils.extentions.customObserver
import com.cardio.physician.ui.common.utils.extentions.getTrimmedText
import com.cardio.physician.ui.common.utils.extentions.isConnectedOrThrowMsg
import com.cardio.physician.ui.common.utils.inputfilter.DecimalDigitsInputFilter
import com.cardio.physician.ui.common.utils.textwatcher.LabelVisiblityHelper
import com.cardio.physician.ui.common.utils.validation.FieldType
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class HealthLogsActivity : BaseToolbarActivity() {

    private var selectedDate: Date? = null
    private val binding by viewBinding(ActivityHealthLogsBinding::inflate)
    private val viewModel: HealthLogsViewModel by viewModels()

    companion object {
        /*fun start(activity: Activity) {
            getIntent(activity).run {
                activity.startActivity(this)
            }
        }*/

        fun getIntent(activity: Activity, userId:String?) : Intent {
            val intent = Intent(activity, HealthLogsActivity::class.java)
            intent.putExtra(EXTRAS.USER_ID, userId)
            return intent
        }
    }

    @Inject
    lateinit var labelVisiblityHelper: LabelVisiblityHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setListeners()
        setViews()
        setObservers()
    }

    override fun getToolbarImp(): IToolbar {
        var toolbar = HealthLogsToolbarImp(binding.headerView.toolBarContainer)
        toolbar.onBackClick = {
            onBackPressed()
        }
        return toolbar
    }

    private fun setObservers() {
        viewModel.getUpdateLiveData().customObserver(this, onLoading = ::showProgress, {
            showToast(baseContext, getString(R.string.succss_response_health_log))
            setResult(RESULT_OK)
            finish()
        }, onError = ::onError
        )
        viewModel.getHealthlogsLiveData().customObserver(this, onLoading = ::showProgress, {
            clearAllFields()
            it?.let {
                setFitnessViews(it)
            }
        }, onError = {msg,exp->
            onError(msg)
            clearAllFields()
        })
    }

    private fun clearAllFields() {
        binding.clHealthDetail.edtWeight.text?.clear()
        binding.clHealthDetail.edtHeartRate.text?.clear()
        binding.clHealthDetail.edtTopBp.text?.clear()
        binding.clHealthDetail.edtBottomBp.text?.clear()
        binding.clHealthDetail.edtStepCount.text?.clear()
    }

    private fun setFitnessViews(it: FitnessModel) {
        it.weight?.let { binding.clHealthDetail.edtWeight.setText(it) }
        it.heartRate?.let { binding.clHealthDetail.edtHeartRate.setText(it) }
        it.bloodPressureTopBp?.let { binding.clHealthDetail.edtTopBp.setText(it) }
        it.bloodPressureBottomBp?.let { binding.clHealthDetail.edtBottomBp.setText(it) }
        it.stepCount?.let { binding.clHealthDetail.edtStepCount.setText(it) }
    }

    private fun setViews() {
        setInputFilters()
        enableButtonSave(false)
        labelVisiblityHelper.addView(
            binding.clHealthDetail.edtWeight,
            binding.clHealthDetail.tvWeightError,
            binding.clHealthDetail.tvWeight,
            null,
            this
        )
        labelVisiblityHelper.addView(
            binding.clHealthDetail.edtHeartRate,
            binding.clHealthDetail.tvErrorHeartRate,
            binding.clHealthDetail.txtHeartRate,
            null,
            this
        )
        labelVisiblityHelper.addView(
            binding.clHealthDetail.edtTopBp,
            binding.clHealthDetail.tvTopBpErrro,
            binding.clHealthDetail.txtTopBp,
            null,
            this
        )
        labelVisiblityHelper.addView(
            binding.clHealthDetail.edtBottomBp,
            binding.clHealthDetail.tvBottomBpErrror,
            binding.clHealthDetail.txtBottomBp,
            null,
            this
        )
        labelVisiblityHelper.addView(
            binding.clHealthDetail.edtStepCount,
            binding.clHealthDetail.tvStepCountError,
            binding.clHealthDetail.textStepCount,
            null,
            this
        )
    }

    private fun setInputFilters() {
        binding.clHealthDetail.edtWeight.filters =
            arrayOf<InputFilter>(DecimalDigitsInputFilter(WEIGHT_DIGITS_BEFORE_ZERO, 2))
        binding.clHealthDetail.edtBottomBp.filters =
            arrayOf<InputFilter>(DecimalDigitsInputFilter(HEART_DIGITS_BEFORE_ZERO, 2))
        binding.clHealthDetail.edtTopBp.filters =
            arrayOf<InputFilter>(DecimalDigitsInputFilter(TOP_BP_DIGITS_BEFORE_ZERO, 2))
        binding.clHealthDetail.edtHeartRate.filters =
            arrayOf<InputFilter>(DecimalDigitsInputFilter(BOTTOM_BP_DIGITS_BEFORE_ZERO, 2))
    }

    private fun setListeners() {
        binding.btSaveLog.setOnClickListener {
            onSubmitClick()
        }
        binding.edtHealthLogDate.setOnClickListener {
            val dateStart = Date()
            var calender = Calendar.getInstance().clearHoursMins().apply {
                add(Calendar.DAY_OF_YEAR, -90)
            }
            dateStart.time = calender.timeInMillis
            //if user created date is found
          /*  viewModel.getUserSignedUpDate()?.let {
                calender.timeInMillis = it
                var calender = calender.clearHoursMins().apply {
                    add(Calendar.DAY_OF_YEAR, -90)
                }
                dateStart.time = calender.timeInMillis
            }*/
            getDatePicker(HealthLogsActivity@ this,
                selectedDate,
                dateStart) { _, year, month, date ->
                val dateString = getDate(date).plus("-")
                    .plus(getMonthNumber(month).toString().plus("-"))
                    .plus(year)
                selectedDate =
                    dateString.datePickerStringToDate(DateFormat_.DATE_FORMAT_DD_MM_YYYY_DATE_PICKER)
                binding.edtHealthLogDate.setText(getStringFromDate(selectedDate))
                isConnectedOrThrowMsg {
                    viewModel.getHealthLogsByDate(binding.edtHealthLogDate.text.toString(), intent.getStringExtra(EXTRAS.USER_ID))
                }
            }?.show()
        }
        var listFieldsf = listOf<EditText>(binding.edtHealthLogDate,
            binding.clHealthDetail.edtWeight,
            binding.clHealthDetail.edtHeartRate,
            binding.clHealthDetail.edtBottomBp,
            binding.clHealthDetail.edtTopBp,
            binding.clHealthDetail.edtStepCount)
        viewModel.addTextChangeListener(listFieldsf) {
            setAlphaVisibility()
        }
    }

    private fun setAlphaVisibility() {
        val weight = binding.clHealthDetail.edtWeight.getTrimmedText()
        val heartRate = binding.clHealthDetail.edtHeartRate.getTrimmedText()
        val topBp = binding.clHealthDetail.edtTopBp.getTrimmedText()
        val bottomBp = binding.clHealthDetail.edtBottomBp.getTrimmedText()
        val stepCount = binding.clHealthDetail.edtStepCount.getTrimmedText()
        val selectedDate = binding.edtHealthLogDate.text.toString().trim()
        viewModel.checkValidation(weight,
            heartRate,
            topBp,
            bottomBp,
            stepCount,
            this.selectedDate,
            {
                enableButtonSave(true)
            },
            { errorMsg, validationModel ->
                enableButtonSave(false)
            },
            {
                enableButtonSave(false)
            })
    }

    private fun enableButtonSave(isEnable: Boolean) {
        if (isEnable) {
            binding.btSaveLog.isEnabled = true
            binding.btSaveLog.alpha = 1.0F
        } else {
            binding.btSaveLog.isEnabled = false
            binding.btSaveLog.alpha = 0.3F
        }
    }

    private fun onSubmitClick() {
        val weight = binding.clHealthDetail.edtWeight.getTrimmedText()
        val heartRate = binding.clHealthDetail.edtHeartRate.getTrimmedText()
        val topBp = binding.clHealthDetail.edtTopBp.getTrimmedText()
        val bottomBp = binding.clHealthDetail.edtBottomBp.getTrimmedText()
        val stepCount = binding.clHealthDetail.edtStepCount.getTrimmedText()
        val selectedDate = binding.edtHealthLogDate.text.toString().trim()
        viewModel.checkValidation(weight,
            heartRate,
            topBp,
            bottomBp,
            stepCount,
            this.selectedDate,
            {
                isConnectedOrThrowMsg {
                    viewModel.updateData(
                        weight,
                        heartRate,
                        topBp,
                        bottomBp,
                        selectedDate,
                        this.selectedDate?.time,
                        stepCount,
                        intent.getStringExtra(EXTRAS.USER_ID)
                    )
                }
            },
            { errorMsg, validationModel ->
                onError(errorMsg)
                setViewsForValidations(validationModel)
            },
            {
                setViewsForValidations(it)
            })
    }

    private fun setViewsForValidations(it: ValidationModelV2) {
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

    private fun setValidationOnViews(
        fieldType: FieldType,
        edtRoundedCorner: Int,
        visibility: Int,
        message: String,
    ) {
        var editText: EditText? = null
        var txtError: TextView? = null
        when (fieldType) {
            FieldType.LOG_DATE -> {
                editText = binding.edtHealthLogDate
                txtError = binding.tvLogDateError
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

    override fun onBackPressed() {
         showConfirmAlertDialog(
           this!!,
           "",
           getString(R.string.confirm_dismiss_diagnosis)
       ) { btnText: String, dialog: DialogInterface ->
           when (btnText) {
               getString(R.string.yes) -> {
                   finish()
               }
           }
       }
    }

}
package com.cardio.physician.ui.views.healthlogs

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import com.cardio.physician.R
import com.cardio.physician.databinding.ActivityHealthLogsBinding
import com.cardio.physician.domain.common.model.validation.ValidationModelV2
import com.cardio.physician.network.Status
import com.cardio.physician.ui.common.base.activity.BaseToolbarActivity
import com.cardio.physician.ui.common.base.toolbar.HealthLogsToolbarImp
import com.cardio.physician.ui.common.base.toolbar.IToolbar
import com.cardio.physician.ui.common.utils.*
import com.cardio.physician.ui.common.utils.extentions.customObserver
import com.cardio.physician.ui.common.utils.extentions.getTrimmedText
import com.cardio.physician.ui.common.utils.extentions.isConnectedOrThrowMsg
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
        fun start(activity: Activity) {
            Intent(activity, HealthLogsActivity::class.java).run {
                activity.startActivity(this)
            }
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
            onBackPressed()
        }, onError = ::onError)
    }

    private fun setViews() {
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
    }

    private fun setListeners() {
        binding.btSaveLog.setOnClickListener {
            onSubmitClick()
        }
        binding.edtHealthLogDate.setOnClickListener {
            val dateStart=Date()
            var calender=Calendar.getInstance()
            calender.add(Calendar.MONTH,-1)
            dateStart.time=calender.timeInMillis
            //if user created date is found
            viewModel.getUserSignedUpDate()?.let { dateStart.time=it }
            getDatePicker(HealthLogsActivity@ this,null, dateStart) { _, year, month, date ->
                val dateString = getDate(date).plus("-")
                    .plus(getMonthNumber(month).toString().plus("-"))
                    .plus(year)
                selectedDate = dateString.datePickerStringToDate(DateFormat_.DATE_FORMAT_DD_MM_YYYY_DATE_PICKER)
                binding.edtHealthLogDate.setText(getStringFromDate(selectedDate))
            }?.show()
        }
        var listFieldsf= listOf<EditText>(binding.clHealthDetail.edtWeight,binding.clHealthDetail.edtHeartRate,binding.clHealthDetail.edtBottomBp,binding.clHealthDetail.edtTopBp)
        viewModel.addTextChangeListener(listFieldsf) {
            setAlphaVisibility()
        }
    }

    private fun setAlphaVisibility() {
        val weight = binding.clHealthDetail.edtWeight.getTrimmedText()
        val heartRate = binding.clHealthDetail.edtHeartRate.getTrimmedText()
        val topBp = binding.clHealthDetail.edtTopBp.getTrimmedText()
        val bottomBp = binding.clHealthDetail.edtBottomBp.getTrimmedText()
        val selectedDate = binding.edtHealthLogDate.text.toString().trim()
        viewModel.checkValidation(weight, heartRate, topBp, bottomBp, this.selectedDate, {
           enableButtonSave(true)
        },{errorMsg,validationModel->
            enableButtonSave(false)
        },{
            enableButtonSave(true)
        })
    }

    private fun enableButtonSave(isEnable: Boolean) {
        if(isEnable){
            binding.btSaveLog.isEnabled=true
            binding.btSaveLog.alpha= 1.0F
        }else{
            binding.btSaveLog.isEnabled=false
            binding.btSaveLog.alpha= 0.3F
        }
    }

    private fun onSubmitClick() {
        val weight = binding.clHealthDetail.edtWeight.getTrimmedText()
        val heartRate = binding.clHealthDetail.edtHeartRate.getTrimmedText()
        val topBp = binding.clHealthDetail.edtTopBp.getTrimmedText()
        val bottomBp = binding.clHealthDetail.edtBottomBp.getTrimmedText()
        val selectedDate = binding.edtHealthLogDate.text.toString().trim()
        viewModel.checkValidation(weight, heartRate, topBp, bottomBp, this.selectedDate, {
           isConnectedOrThrowMsg {
               viewModel.updateData(
                   weight,
                   heartRate,
                   topBp,
                   bottomBp,
                   selectedDate,
                   this.selectedDate?.time
               )
           }
        },{errorMsg,validationModel->
            onError(errorMsg)
            setViewsForValidations(validationModel)
        },{
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
        message: String
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

}
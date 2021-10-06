package com.cardio.doctor.ui.views.diagnosis.step2

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cardio.doctor.R
import com.cardio.doctor.databinding.FragmentDiagnosisPart2Binding
import com.cardio.doctor.ui.views.diagnosis.common.BaseDiagnosisFragment
import com.google.android.material.bottomsheet.BottomSheetDialog

class DiagnosisFragmentStep2 : BaseDiagnosisFragment<FragmentDiagnosisPart2Binding>() {

    private  var mBottomSheetDialog: BottomSheetDialog?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiagnosisPart2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.cvDiagnosisBottomContainer.btNext.setOnClickListener {
            findNavController().navigate(DiagnosisFragmentStep2Directions.actionDiagnosisFragmentPart2ToDiagnosisFragmentPart3())
        }
        binding.cvDiagnosisBottomContainer.btCancel.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.materialCardViewImage.setOnClickListener { showFilePickOptions() }
    }

    private fun showFilePickOptions() {
        if (mBottomSheetDialog != null && mBottomSheetDialog!!.isShowing()) {
            mBottomSheetDialog!!.dismiss()
        }
        mBottomSheetDialog = BottomSheetDialog(parentActivity!!,R.style.CustomBottomSheetDialogTheme)
        val sheetView: View =
            parentActivity!!.layoutInflater.inflate(R.layout.bottom_sheet_image_picker, null)
        mBottomSheetDialog?.setContentView(sheetView)
        mBottomSheetDialog?.show()
        sheetView.findViewById<View>(R.id.tvTakePic).setOnClickListener {
            takePicture()
            dismissFilePicker()
        }
        sheetView.findViewById<View>(R.id.tvPickImage).setOnClickListener {
            pickImage()
            dismissFilePicker()
        }
    }

    fun dismissFilePicker() {
        Handler(Looper.getMainLooper()).postDelayed(
            { if (mBottomSheetDialog != null && mBottomSheetDialog!!.isShowing) mBottomSheetDialog!!.dismiss() },
            500
        )
    }


    private fun pickImage() {
    }

    private fun takePicture() {

    }



}
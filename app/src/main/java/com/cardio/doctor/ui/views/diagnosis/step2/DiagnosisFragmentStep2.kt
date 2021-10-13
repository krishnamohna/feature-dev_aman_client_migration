package com.cardio.doctor.ui.views.diagnosis.step2

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cardio.doctor.R
import com.cardio.doctor.databinding.FragmentDiagnosisPart2Binding
import com.cardio.doctor.ui.common.utils.extentions.customObserver
import com.cardio.doctor.ui.common.utils.showFilePickOptions
import com.cardio.doctor.ui.common.utils.showToast
import com.cardio.doctor.ui.views.diagnosis.common.BaseDiagnosisFragment
import com.cardio.doctor.ui.views.diagnosis.step2.adapter.MedRecylarAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class DiagnosisFragmentStep2 : BaseDiagnosisFragment<FragmentDiagnosisPart2Binding>() {

    private val viewModel: DiagnosisStep2ViewModel by viewModels()
    private val adapterMed: MedRecylarAdapter by lazy { MedRecylarAdapter() }

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
        setViews()
        setObservers()
    }

    private var resultLauncherSpeechToText: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val result = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                result?.isNotEmpty()?.let {
                    binding.edtMedicineSearch.setText(result.get(0))
                    binding.imageViewAddMed.performClick()
                }
            }
        }

    private val requestMultiplePermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            var isGranted = true
            permissions.entries.forEach {
                if (it.value == false) {
                    isGranted = false
                    return@forEach
                }
            }
            if (isGranted)
                launchSpeechToText()
        }

    private fun launchSpeechToText() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE,
            Locale.getDefault()
        )
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text")
        try{
            resultLauncherSpeechToText.launch(intent)
        }catch(exp:Exception){
            showToast(parentActivity!!, getString(R.string.device_does_not_support_speech_to_text))
            promptToInstallApp()
        }
    }

    private fun promptToInstallApp() {
        val appPackageName = "com.google.android.googlequicksearchbox"
        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$appPackageName")
                )
            )
        } catch (anfe: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                )
            )
        }
    }

    private fun setViews() {
        binding.recyclarAddedMed.layoutManager = LinearLayoutManager(parentActivity!!)
        binding.recyclarAddedMed.adapter = adapterMed
        binding.cvDiagnosisBottomContainer.btCancel.setText(getString(R.string.back))
        setStepView(binding.stepView.stepView)
    }


    private fun setListeners() {
        binding.cvDiagnosisBottomContainer.btNext.setOnClickListener {
            findNavController().navigate(DiagnosisFragmentStep2Directions.actionDiagnosisFragmentPart2ToDiagnosisFragmentPart3())
        }
        binding.cvDiagnosisBottomContainer.btCancel.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.materialCardViewImage.setOnClickListener {
            parentActivity?.let {
                showFilePickOptions(
                    activity = it
                )
            }
        }
        binding.materialCardViewSpeech.setOnClickListener {
            checkPermission()
        }
        binding.imageViewAddMed.setOnClickListener {
            binding.edtMedicineSearch.text.toString().trim().let {
                if(it.isNotEmpty()){
                    viewModel.searchMed(binding.edtMedicineSearch.text.toString().trim())
                }else{
                    showToast(parentActivity!!, getString(R.string.enter_medicine))
                }
            }
        }
    }

    private fun checkPermission() {
        if (isAdded) {
            requestMultiplePermission.launch(
                arrayOf(
                    Manifest.permission.RECORD_AUDIO
                )
            )
        }
    }

    private fun setObservers() {
        viewModel.getMedicineLiveData().customObserver(
            viewLifecycleOwner,
            onLoading = {
                showProgress(it)
            },
            onSuccess = {
                binding.edtMedicineSearch.setText("")
                parentActivity?.let { showToast(it, "Medicine Added ") }
                it?.let { adapterMed.addMed(it) }
            },
            onError = { msg ->
                parentActivity?.let { showToast(it, msg ?: getString(R.string.getting_some_error)) }
            }
        )
    }


}

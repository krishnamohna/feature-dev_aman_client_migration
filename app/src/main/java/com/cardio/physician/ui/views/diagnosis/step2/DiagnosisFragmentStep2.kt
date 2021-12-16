package com.cardio.physician.ui.views.diagnosis.step2

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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.cardio.physician.R
import com.cardio.physician.databinding.FragmentDiagnosisPart2Binding
import com.cardio.physician.ui.common.utils.Constants
import com.cardio.physician.ui.common.utils.EXTRAS.TEXT_RECOGNIZATION
import com.cardio.physician.ui.common.utils.ItemOffsetDecoration
import com.cardio.physician.ui.common.utils.extentions.customObserver
import com.cardio.physician.ui.common.utils.extentions.isConnectedOrThrowMsg
import com.cardio.physician.ui.common.utils.showFilePickOptions
import com.cardio.physician.ui.common.utils.showToast
import com.cardio.physician.ui.views.diagnosis.common.BaseDiagnosisFragment
import com.cardio.physician.ui.views.diagnosis.step1.DiagnosisFragmentStep1Args
import com.cardio.physician.ui.views.diagnosis.step2.adapter.MedRecylarAdapter
import com.google.mlkit.vision.demo.kotlin.StillImageActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class DiagnosisFragmentStep2 : BaseDiagnosisFragment<FragmentDiagnosisPart2Binding>() {

    private val viewModel: DiagnosisStep2ViewModel by viewModels()
    private val adapterMed: MedRecylarAdapter by lazy { MedRecylarAdapter() }
    val args: DiagnosisFragmentStep2Args by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
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

    private val requestSpeechPermissions =
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

    private val requestStoragePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            var isGranted = true
            permissions.entries.forEach {
                if (it.value == false) {
                    isGranted = false
                    return@forEach
                }
            }
            if (isGranted)
                onStoragePermissonGranted()
        }

    private val resultTextRecog=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
        if (result.resultCode == Activity.RESULT_OK) {
            val result = result.data?.getStringExtra(TEXT_RECOGNIZATION)
            result?.let {result->
                binding.edtMedicineSearch.setText(result)
                binding.imageViewAddMed.performClick()
            }
        }
    }

    private fun onStoragePermissonGranted() {
        parentActivity?.let {
            showFilePickOptions(activity = it, {
                resultTextRecog.launch(StillImageActivity.getIntent(requireActivity(),StillImageActivity.CHOOSE_TYPE_CAMERA))
            }, {
                resultTextRecog.launch(StillImageActivity.getIntent(requireActivity(),StillImageActivity.CHOOSE_TYPE_GALLERY))
            })
        }
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
        try {
            resultLauncherSpeechToText.launch(intent)
        } catch (exp: Exception) {
            showToast(parentActivity!!, getString(R.string.device_does_not_support_speech_to_text))
            promptToInstallApp()
        }
    }

    private fun promptToInstallApp() {
        val appPackageName = Constants.GOOGLE_SPEECH_SEARCH_APP
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
        binding.recyclarAddedMed.layoutManager = GridLayoutManager(requireContext(), 2)
        val itemDecoration = ItemOffsetDecoration(requireContext(), R.dimen.item_offset)
     //   binding.recyclarAddedMed.addItemDecoration(itemDecoration)
        binding.recyclarAddedMed.adapter = adapterMed
        binding.cvDiagnosisBottomContainer.btCancel.setText(getString(R.string.back))
        setStepView(binding.stepView.stepView)
    }


    private fun setListeners() {
        binding.cvDiagnosisBottomContainer.btNext.setOnClickListener {
            diagnosisActivity?.getDiagnosisModel()?.medications = adapterMed.listMeds
            isConnectedOrThrowMsg {
                findNavController().navigate(DiagnosisFragmentStep2Directions.actionDiagnosisFragmentPart2ToDiagnosisFragmentPart3(args.userId))
            }
        }
        binding.cvDiagnosisBottomContainer.btCancel.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.materialCardViewImage.setOnClickListener {
            checkStoragePermission()
        }
        binding.materialCardViewSpeech.setOnClickListener {
            checkSpeechPermission()
        }
        binding.imageViewAddMed.setOnClickListener {
            binding.edtMedicineSearch.text.toString().trim().let {
                if (it.isNotEmpty()) {
                    if (adapterMed.isMedSearchExist(it)) {
                        showToast(parentActivity!!, getString(R.string.error_med_exist))
                        return@setOnClickListener
                    }
                    isConnectedOrThrowMsg {
                        viewModel.searchMed(binding.edtMedicineSearch.text.toString().trim())
                    }
                } else {
                    showToast(parentActivity!!, getString(R.string.enter_medicine))
                }
            }
        }
    }

    private fun checkStoragePermission() {
        if (isAdded) {
            requestStoragePermissions.launch(
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            )
        }
    }

    private fun checkSpeechPermission() {
        if (isAdded) {
            requestSpeechPermissions.launch(
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
                it?.let {
                    binding.edtMedicineSearch.setText("")
                    if (!adapterMed.isMedSearchExist(it.searchedMed!!) && !adapterMed.isMedExist(it.searchedMed)) {
                        adapterMed.addMed(it)
                        parentActivity?.let { showToast(it, "Medicine Added ") }
                    } else {
                        parentActivity?.let { showToast(it, getString(R.string.error_med_exist)) }
                    }
                }
            },
            onError = { msg ->
                parentActivity?.let { showToast(it, msg ?: getString(R.string.getting_some_error)) }
            }
        )
    }


}

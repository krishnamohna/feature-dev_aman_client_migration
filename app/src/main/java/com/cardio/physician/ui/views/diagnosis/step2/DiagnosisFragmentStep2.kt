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
import androidx.recyclerview.widget.LinearLayoutManager
import com.cardio.doctor.ui.views.diagnosis.step2.adapter.MedRecylarAdapter
import com.cardio.physician.ui.views.diagnosis.step2.adapter.PredictiveMedicineSearchAdapter
import com.cardio.physician.R
import com.cardio.physician.databinding.FragmentDiagnosisPart2Binding
import com.cardio.physician.domain.diagnosis.MedicineModel
import com.cardio.physician.ui.common.utils.*
import com.cardio.physician.ui.common.utils.EXTRAS.TEXT_RECOGNIZATION
import com.cardio.physician.ui.common.utils.extentions.customObserver
import com.cardio.physician.ui.common.utils.extentions.isConnectedOrThrowMsg
import com.cardio.physician.ui.views.diagnosis.common.BaseDiagnosisFragment
import com.cardio.physician.ui.views.diagnosis.step2.dosageandfrequency.DosageAndFrequencyActivity
import com.google.mlkit.vision.demo.kotlin.StillImageActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import java.util.*
import kotlin.coroutines.CoroutineContext


@AndroidEntryPoint
open class DiagnosisFragmentStep2 : BaseDiagnosisFragment<FragmentDiagnosisPart2Binding>(),
    CoroutineScope {

    private lateinit var adapterPredictiveSearch: PredictiveMedicineSearchAdapter
    val viewModel: DiagnosisStep2ViewModel by viewModels()
    val args: DiagnosisFragmentStep2Args by navArgs()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private lateinit var job: Job

    private val adapterMed: MedRecylarAdapter by lazy {
        MedRecylarAdapter {
            if (it.hasDosage())
                resultEditMed.launch(DosageAndFrequencyActivity.getIntent(it, requireActivity()))
        }
    }

    private val resultEditMed: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it?.resultCode == Activity.RESULT_OK) {
                it.data?.getParcelableExtra<MedicineModel>(EXTRAS.EXTRAS_DOCTOR_ID)?.let {
                    adapterMed.updateMedicine(it)
                }
            }
        }


    private val resultAddMed: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it?.resultCode == Activity.RESULT_OK) {
                it.data?.getParcelableExtra<MedicineModel>(EXTRAS.EXTRAS_DOCTOR_ID)?.let {
                    addToList(it)
                }
            }
        }

    private val onMedicineSearch: (query: String) -> List<MedicineModel> = { query ->
        searchMedicine(query)
    }

    private fun searchMedicine(query: String): List<MedicineModel> {
        medSearchProgressVisibility(View.VISIBLE)
        return viewModel.searchMedicine(query).let {
            medSearchProgressVisibility(View.GONE)
            it
        }
    }

    private fun medSearchProgressVisibility(visibility: Int) {
        diagnosisActivity?.run {
            runOnUiThread {
                binding.pbMedSearch.visibility = visibility
            }
        }
    }

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
        job = Job()
        setListeners()
        setViews()
        setObservers()
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        job.cancel()
    }

    private fun init() {

    }

    private fun setViews() {
        binding.recyclarAddedMed.layoutManager = LinearLayoutManager(requireContext())
        val itemDecoration = ItemOffsetDecoration(requireContext(), R.dimen.item_offset)
        //   binding.recyclarAddedMed.addItemDecoration(itemDecoration)
        binding.recyclarAddedMed.adapter = adapterMed
        binding.cvDiagnosisBottomContainer.btCancel.setText(getString(R.string.back))
        setStepView(binding.stepView.stepView)
        var list = listOf<MedicineModel>()
        adapterPredictiveSearch = PredictiveMedicineSearchAdapter(
            requireContext(),
            R.layout.item_medicine_search_layout,
            list, onMedicineSearch
        )
        binding.edtMedicineSearch.setAdapter(adapterPredictiveSearch)
    }

    private var resultLauncherSpeechToText: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val result = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                result?.isNotEmpty()?.let {
                    binding.edtMedicineSearch.setText(result[0])
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
                onStoragePermissionGranted()
        }

    private val resultTextRecog =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val result = result.data?.getStringExtra(TEXT_RECOGNIZATION)
                result?.let { result ->
                    binding.edtMedicineSearch.setText(result)
                    binding.imageViewAddMed.performClick()
                }
            }
        }

    private fun onStoragePermissionGranted() {
        parentActivity?.let {
            showFilePickOptions(activity = it, {
                resultTextRecog.launch(
                    StillImageActivity.getIntent(
                        requireActivity(),
                        StillImageActivity.CHOOSE_TYPE_CAMERA
                    )
                )
            }, {
                resultTextRecog.launch(
                    StillImageActivity.getIntent(
                        requireActivity(),
                        StillImageActivity.CHOOSE_TYPE_GALLERY
                    )
                )
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
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speak_to_text))
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


    private fun setListeners() {
        binding.cvDiagnosisBottomContainer.btNext.setOnClickListener {
            diagnosisActivity?.diagnosisModel?.medications = adapterMed.listMeds
            onNextButtonClick()
        }
        binding.cvDiagnosisBottomContainer.btCancel.setOnClickListener {
            onBottomBackButtonClick()
        }
        binding.materialCardViewImage.setOnClickListener {
            checkStoragePermission()
        }
        binding.materialCardViewSpeech.setOnClickListener {
            checkSpeechPermission()
        }
        binding.imageViewAddMed.setOnClickListener {
            onAddMed(false)
        }
        binding.edtMedicineSearch.setOnItemClickListener { adapterView, view, position, l ->
            binding.edtMedicineSearch.setText(adapterPredictiveSearch.getMedName(position))
            binding.edtMedicineSearch.setSelection(binding.edtMedicineSearch.text.length)
            onAddMed(true)
        }
    }

    private fun onAddMed(isPreExistedMed: Boolean) {
        binding.edtMedicineSearch.text.toString().trim().let {
            if (it.isNotEmpty()) {
                if (adapterMed.isMedSearchExist(it)) {
                    showToast(parentActivity!!, getString(R.string.error_med_exist))
                    return
                }
                isConnectedOrThrowMsg {
                    viewModel.fetchMed(
                        binding.edtMedicineSearch.text.toString().trim(),
                        isPreExistedMed
                    )
                }
            } else {
                showToast(parentActivity!!, getString(R.string.enter_medicine))
            }
        }
    }

    open fun onBottomBackButtonClick() {
        findNavController().popBackStack()
    }

    open fun onNextButtonClick() {
        isConnectedOrThrowMsg {
            findNavController().navigate(DiagnosisFragmentStep2Directions.actionDiagnosisFragmentPart2ToDiagnosisFragmentPart3(args.userId))
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

    open fun setObservers() {
        viewModel.getMedicineLiveData().customObserver(
            viewLifecycleOwner,
            onLoading = {
                showProgress(it)
            },
            onSuccess = {
                it?.let {
                    binding.edtMedicineSearch.setText("")
                    if (!doesMedAlreadyExist(it)) {
                        if (it.hasDosage())
                            resultAddMed.launch(
                                DosageAndFrequencyActivity.getIntent(
                                    it,
                                    requireActivity()
                                )
                            )
                        else
                            addToList(it)
                    } else {
                        parentActivity?.let { showToast(it, getString(R.string.error_med_exist)) }
                    }
                }
            },
            onError = { msg, exp ->
                parentActivity?.let { showToast(it, msg ?: getString(R.string.getting_some_error)) }
            }
        )
    }

    private fun doesMedAlreadyExist(medicineModel: MedicineModel): Boolean =
        adapterMed.isMedSearchExist(medicineModel.searchedMed!!) || adapterMed.isMedExist(
            medicineModel.name!!
        )

    open fun addToList(it: MedicineModel) {
        adapterMed.addMed(it)
    }

}

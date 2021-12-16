/*
 * Copyright 2020 Google LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.mlkit.vision.demo.kotlin

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.Pair
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import com.cardio.physician.R
import com.cardio.physician.databinding.ActivityStillImageBinding
import com.cardio.physician.ui.common.base.activity.BaseToolbarActivity
import com.cardio.physician.ui.common.base.toolbar.IToolbar
import com.cardio.physician.ui.common.base.toolbar.TextRecogToolbarImp
import com.cardio.physician.ui.common.utils.EXTRAS
import com.cardio.physician.ui.common.utils.showConfirmAlertDialog
import com.cardio.physician.ui.views.textrecognization.BitmapUtils
import com.cardio.physician.ui.views.textrecognization.VisionImageProcessor
import com.google.android.gms.common.annotation.KeepName
import com.google.android.material.chip.Chip
import com.google.mlkit.vision.demo.kotlin.textdetector.TextRecognitionProcessorForStillimage
import java.io.IOException
import java.util.*

/** Activity demonstrating different image detector features with a still image from camera.  */
@KeepName
class StillImageActivity : BaseToolbarActivity() {

    private var selectedMode =
        TEXT_RECOGNITION
    private var selectedSize: String? =
        SIZE_SCREEN
    private var isLandScape = false
    private var imageUri: Uri? = null

    // Max width (portrait mode)
    private var imageMaxWidth = 0

    // Max height (portrait mode)
    private var imageMaxHeight = 0
    private var imageProcessor: VisionImageProcessor? = null
    private val binding by viewBinding(ActivityStillImageBinding::inflate)

    var onBackClick: (() -> Unit)? = {
        onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        populateFeatureSelector()
        populateSizeSelector()
        isLandScape =
            resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        if (savedInstanceState != null) {
            imageUri =
                savedInstanceState.getParcelable(KEY_IMAGE_URI)
            imageMaxWidth =
                savedInstanceState.getInt(KEY_IMAGE_MAX_WIDTH)
            imageMaxHeight =
                savedInstanceState.getInt(KEY_IMAGE_MAX_HEIGHT)
            selectedSize =
                savedInstanceState.getString(KEY_SELECTED_SIZE)
        }

        val rootView = findViewById<View>(R.id.root)
        rootView.viewTreeObserver.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    rootView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    imageMaxWidth = rootView.width
                    imageMaxHeight =
                        rootView.height - findViewById<View>(R.id.control).height
                    if (SIZE_SCREEN == selectedSize) {
                        tryReloadAndDetectInImage()
                    }
                }
            })
        setListner()
        init()
    }

    private fun init() {
        if (intent.getIntExtra(EXTRAS.CHOOSE_TYPE, CHOOSE_TYPE_GALLERY) == CHOOSE_TYPE_CAMERA) {
            startCameraIntentForResult()
        } else {
            startChooseImageIntentForResult()
        }
    }

    private fun setListner() {
        binding.chipGroupTextRecog.setOnCheckedChangeListener { group, checkedId ->
            group.findViewById<Chip>(checkedId)?.let {
                var chipText = it.text.toString()
                showConfirmAlertDialog(this,
                    getString(R.string.confirm_drug),
                    chipText) { clickedButton, dialog ->
                    when (clickedButton) {
                        getString(R.string.yes) -> {
                            var intent = Intent().apply {
                                putExtra(EXTRAS.TEXT_RECOGNIZATION, chipText)
                            }
                            setResult(RESULT_OK, intent)
                            finish()
                        }
                        getString(R.string.no) -> {
                            unCheckAllChips()
                        }
                    }
                    dialog.dismiss()
                }
            }
        }
        val settingsButton = findViewById<ImageView>(R.id.settings_button)
        settingsButton.setOnClickListener {
            /*  val intent =
                Intent(
                  applicationContext,
                  SettingsActivity::class.java
                )
              intent.putExtra(
                SettingsActivity.EXTRA_LAUNCH_SOURCE,
                LaunchSource.STILL_IMAGE
              )
              startActivity(intent)*/
        }
        binding.selectImageButton
            .setOnClickListener { view: View ->
                // Menu for selecting either: a) take new photo b) select from existing
                val popup =
                    PopupMenu(this@StillImageActivity, view)
                popup.setOnMenuItemClickListener { menuItem: MenuItem ->
                    val itemId =
                        menuItem.itemId
                    if (itemId == R.id.select_images_from_local) {
                        startChooseImageIntentForResult()
                        return@setOnMenuItemClickListener true
                    } else if (itemId == R.id.take_photo_using_camera) {
                        startCameraIntentForResult()
                        return@setOnMenuItemClickListener true
                    }
                    false
                }
                val inflater = popup.menuInflater
                inflater.inflate(R.menu.camera_button_menu, popup.menu)
                popup.show()
            }

    }

    private fun unCheckAllChips() {
        for (i in 0 until binding.chipGroupTextRecog.childCount)
            (binding.chipGroupTextRecog.getChildAt(i) as? Chip)?.isChecked = false
    }

    override fun getToolbarImp(): IToolbar {
        return TextRecogToolbarImp(binding.headerView.toolBarContainer).apply {
            onBackClick = this@StillImageActivity.onBackClick
        }
    }

    public override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        createImageProcessor()
        tryReloadAndDetectInImage()
    }

    public override fun onPause() {
        super.onPause()
        imageProcessor?.run {
            this.stop()
        }
    }

    public override fun onDestroy() {
        super.onDestroy()
        imageProcessor?.run {
            this.stop()
        }
    }

    private fun populateFeatureSelector() {
        val featureSpinner = findViewById<Spinner>(R.id.feature_selector)
        val options: MutableList<String> = ArrayList()
        options.add(TEXT_RECOGNITION)

        // Creating adapter for featureSpinner
        val dataAdapter =
            ArrayAdapter(this, R.layout.spinner_style, options)
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // attaching data adapter to spinner
        featureSpinner.adapter = dataAdapter
        featureSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View?,
                pos: Int,
                id: Long,
            ) {
                if (pos >= 0) {
                    selectedMode = parentView.getItemAtPosition(pos).toString()
                    createImageProcessor()
                    tryReloadAndDetectInImage()
                }
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {}
        }
    }

    private fun populateSizeSelector() {
        val sizeSpinner = findViewById<Spinner>(R.id.size_selector)
        val options: MutableList<String> = ArrayList()
        options.add(SIZE_SCREEN)
        options.add(SIZE_1024_768)
        options.add(SIZE_640_480)
        options.add(SIZE_ORIGINAL)
        // Creating adapter for featureSpinner
        val dataAdapter =
            ArrayAdapter(this, R.layout.spinner_style, options)
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // attaching data adapter to spinner
        sizeSpinner.adapter = dataAdapter
        sizeSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View?,
                pos: Int,
                id: Long,
            ) {
                if (pos >= 0) {
                    selectedSize = parentView.getItemAtPosition(pos).toString()
                    tryReloadAndDetectInImage()
                }
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {}
        }
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(
            KEY_IMAGE_URI,
            imageUri
        )
        outState.putInt(
            KEY_IMAGE_MAX_WIDTH,
            imageMaxWidth
        )
        outState.putInt(
            KEY_IMAGE_MAX_HEIGHT,
            imageMaxHeight
        )
        outState.putString(
            KEY_SELECTED_SIZE,
            selectedSize
        )
    }

    private fun startCameraIntentForResult() { // Clean up last time's image
        imageUri = null
        binding.preview!!.setImageBitmap(null)
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            val values = ContentValues()
            values.put(MediaStore.Images.Media.TITLE, "New Picture")
            values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera")
            imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            startActivityForResult(
                takePictureIntent,
                REQUEST_IMAGE_CAPTURE
            )
        }
    }

    private fun startChooseImageIntentForResult() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Select Picture"),
            REQUEST_CHOOSE_IMAGE
        )
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
    ) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            tryReloadAndDetectInImage()
        } else if (requestCode == REQUEST_CHOOSE_IMAGE && resultCode == Activity.RESULT_OK) {
            // In this case, imageUri is returned by the chooser, save it.
            imageUri = data!!.data
            tryReloadAndDetectInImage()
        } else {
            super.onActivityResult(requestCode, resultCode, data)
            finish()
        }
    }

    private fun tryReloadAndDetectInImage() {
        Log.d(
            TAG,
            "Try reload and detect image"
        )
        try {
            if (imageUri == null) {
                return
            }

            if (SIZE_SCREEN == selectedSize && imageMaxWidth == 0) {
                // UI layout has not finished yet, will reload once it's ready.
                return
            }

            val imageBitmap = BitmapUtils.getBitmapFromContentUri(contentResolver, imageUri)
                ?: return
            // Clear the overlay first
            binding.graphicOverlay!!.clear()

            val resizedBitmap: Bitmap
            resizedBitmap = if (selectedSize == SIZE_ORIGINAL) {
                imageBitmap
            } else {
                // Get the dimensions of the image view
                val targetedSize: Pair<Int, Int> = targetedWidthHeight

                // Determine how much to scale down the image
                val scaleFactor = Math.max(
                    imageBitmap.width.toFloat() / targetedSize.first.toFloat(),
                    imageBitmap.height.toFloat() / targetedSize.second.toFloat()
                )
                Bitmap.createScaledBitmap(
                    imageBitmap,
                    (imageBitmap.width / scaleFactor).toInt(),
                    (imageBitmap.height / scaleFactor).toInt(),
                    true
                )
            }

            binding.preview!!.setImageBitmap(resizedBitmap)
            if (imageProcessor != null) {
                binding.graphicOverlay!!.setImageSourceInfo(
                    resizedBitmap.width, resizedBitmap.height, /* isFlipped= */false
                )
                imageProcessor!!.processBitmap(resizedBitmap,
                    binding.graphicOverlay,
                    binding.chipGroupTextRecog)
            } else {
                Log.e(
                    TAG,
                    "Null imageProcessor, please check adb logs for imageProcessor creation error"
                )
            }
        } catch (e: IOException) {
            Log.e(
                TAG,
                "Error retrieving saved image"
            )
            //  imageUri = null
        } finally {

        }
    }

    private val targetedWidthHeight: Pair<Int, Int>
        get() {
            val targetWidth: Int
            val targetHeight: Int
            when (selectedSize) {
                SIZE_SCREEN -> {
                    targetWidth = imageMaxWidth
                    targetHeight = imageMaxHeight
                }
                SIZE_640_480 -> {
                    targetWidth = if (isLandScape) 640 else 480
                    targetHeight = if (isLandScape) 480 else 640
                }
                SIZE_1024_768 -> {
                    targetWidth = if (isLandScape) 1024 else 768
                    targetHeight = if (isLandScape) 768 else 1024
                }
                else -> throw IllegalStateException("Unknown size")
            }
            return Pair(targetWidth, targetHeight)
        }

    private fun createImageProcessor() {
        try {
            when (selectedMode) {
                TEXT_RECOGNITION ->
                    imageProcessor =
                        TextRecognitionProcessorForStillimage(this)
                else -> Log.e(
                    TAG,
                    "Unknown selectedMode: $selectedMode"
                )
            }
        } catch (e: Exception) {
            Log.e(
                TAG,
                "Can not create image processor: $selectedMode",
                e
            )
            Toast.makeText(
                applicationContext,
                "Can not create image processor: " + e.message,
                Toast.LENGTH_LONG
            )
                .show()
        }
    }

    companion object {
        private const val TAG = "StillImageActivity"
        private const val TEXT_RECOGNITION = "Text Recognition"
        const val CHOOSE_TYPE_CAMERA = 2
        const val CHOOSE_TYPE_GALLERY = 3
        private const val SIZE_SCREEN = "w:screen" // Match screen width
        private const val SIZE_1024_768 = "w:1024" // ~1024*768 in a normal ratio
        private const val SIZE_640_480 = "w:640" // ~640*480 in a normal ratio
        private const val SIZE_ORIGINAL = "w:original" // Original image size
        private const val KEY_IMAGE_URI = "com.google.mlkit.vision.demo.KEY_IMAGE_URI"
        private const val KEY_IMAGE_MAX_WIDTH = "com.google.mlkit.vision.demo.KEY_IMAGE_MAX_WIDTH"
        private const val KEY_IMAGE_MAX_HEIGHT = "com.google.mlkit.vision.demo.KEY_IMAGE_MAX_HEIGHT"
        private const val KEY_SELECTED_SIZE = "com.google.mlkit.vision.demo.KEY_SELECTED_SIZE"
        private const val REQUEST_IMAGE_CAPTURE = 1001
        private const val REQUEST_CHOOSE_IMAGE = 1002

        fun getIntent(activity: Activity, chooseType: Int): Intent {
            return Intent(activity, StillImageActivity::class.java).apply {
                putExtra(EXTRAS.CHOOSE_TYPE, chooseType)
            }
        }
    }
}

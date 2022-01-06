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

package com.google.mlkit.vision.demo.kotlin.textdetector

import android.content.Context
import android.util.Log
import com.cardio.physician.R
import com.cardio.physician.ui.common.utils.extentions.isAllLetter
import com.cardio.physician.ui.views.textrecognization.GraphicOverlay
import com.google.android.gms.tasks.Task
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.demo.kotlin.VisionProcessorBase
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer


/** Processor for the text detector demo.  */
class TextRecognitionProcessorForStillimage(val context: Context) :
    VisionProcessorBase<Text>(context) {

    private val textRecognizer: TextRecognizer = TextRecognition.getClient()
    var states = arrayOf(intArrayOf(android.R.attr.state_enabled),
        intArrayOf(-android.R.attr.state_enabled),
        intArrayOf(-android.R.attr.state_checked),
        intArrayOf(android.R.attr.state_pressed))
    /*var colors = intArrayOf(
        context.getc,
        Color.RED,
        Color.GREEN,
        Color.BLUE
    )
    var chipColorList = ColorStateList(states, colors)*/


    override fun stop() {
        super.stop()
        textRecognizer.close()
    }

    override fun detectInImage(image: InputImage): Task<Text> {
        return textRecognizer.process(image)
    }

    override fun onSuccess(text: Text, graphicOverlay: GraphicOverlay, chipGroup: ChipGroup?) {
        Log.d(TAG, "On-device Text detection successful")
        //  logExtrasForTesting(text)
        //  graphicOverlay.add(TextGraphic(graphicOverlay, text))
        chipGroup?.removeAllViews()
        chipGroup?.let {
            addChipsToGroup(it, text)
        }
    }

    private fun addChipsToGroup(chipGroup: ChipGroup, text: Text) {
        for (i in text.textBlocks.indices) {
            val lines = text.textBlocks[i].lines
            for (j in lines.indices) {
                val elements =
                    lines[j].elements
                for (k in elements.indices) {
                    val element = elements[k]
                    if (!isTextValid(element.text))
                        continue
                    val chip = Chip(chipGroup.context)
                    chip.text = element.text
                    chip.isClickable = true
                    chip.isCheckable = true
                    chip.setTextColor(context.getColorStateList(R.color.selector_text_chip))
                    chip.chipBackgroundColor = context.getColorStateList(R.color.selector_chip)
                    chip.isChipIconVisible = false
                    chip.isCheckedIconVisible = false
                    chip.isCloseIconVisible = false
                    chipGroup?.addView(chip)
                }
            }
        }
    }

    private fun isTextValid(text: String): Boolean {
        return text.length > 3 && text.isAllLetter()
    }


    override fun onFailure(e: Exception) {
        Log.w(TAG, "Text detection failed.$e")
    }

    companion object {
        private const val TAG = "TextRecProcessor"
        private fun logExtrasForTesting(text: Text?) {
            if (text != null) {
                Log.v(
                    MANUAL_TESTING_LOG,
                    "Detected text has : " + text.textBlocks.size + " blocks"
                )
                for (i in text.textBlocks.indices) {
                    val lines = text.textBlocks[i].lines
                    Log.v(
                        MANUAL_TESTING_LOG,
                        String.format("Detected text block %d has %d lines", i, lines.size)
                    )
                    for (j in lines.indices) {
                        val elements =
                            lines[j].elements
                        Log.v(
                            MANUAL_TESTING_LOG,
                            String.format("Detected text line %d has %d elements", j, elements.size)
                        )
                        for (k in elements.indices) {
                            val element = elements[k]
                            Log.v(
                                MANUAL_TESTING_LOG,
                                String.format("Detected text element %d says: %s", k, element.text)
                            )
                            Log.v(
                                MANUAL_TESTING_LOG,
                                String.format(
                                    "Detected text element %d has a bounding box: %s",
                                    k, element.boundingBox!!.flattenToString()
                                )
                            )
                            Log.v(
                                MANUAL_TESTING_LOG,
                                String.format(
                                    "Expected corner point size is 4, get %d",
                                    element.cornerPoints!!.size
                                )
                            )
                            for (point in element.cornerPoints!!) {
                                Log.v(
                                    MANUAL_TESTING_LOG,
                                    String.format(
                                        "Corner point for element %d is located at: x - %d, y = %d",
                                        k, point.x, point.y
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

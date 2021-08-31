@file:Suppress("unused")

package com.cardio.doctor.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import androidx.constraintlayout.motion.utils.Oscillator
import androidx.core.content.FileProvider
import com.cardio.doctor.R
import java.io.*

const val FILES_PATH = "Compressor"
private const val EOF = -1
private const val DEFAULT_BUFFER_SIZE = 1024 * 4


@Throws(IOException::class)
private fun createImageFile(directory: String): File? {
    var imageFile: File? = null
    if (Environment.MEDIA_MOUNTED == Environment
            .getExternalStorageState()
    ) {
        val storageDir = File(directory)
        if (!storageDir.mkdirs()) {
            if (!storageDir.exists()) {
                Log.e("Create Dir", "Failed to create directory")
                return null
            }
        }
        val imageFileName = "JPEG_" + System.currentTimeMillis() + "_"

        imageFile = File.createTempFile(imageFileName, ".jpg", storageDir)
    }
    return imageFile
}


fun saveImageInCache(context : Context, image: Bitmap): Uri? {
    var uri: Uri? = null
    try {

        val file = createImageFile(context.externalCacheDir!!.path)!!
        val stream = FileOutputStream(file)
        image.compress(Bitmap.CompressFormat.PNG, 90, stream)
        stream.flush()
        stream.close()
        uri =
            FileProvider.getUriForFile(context, "com.silo.fileprovider", file)

    } catch (e: IOException) {
        Log.d(
            Oscillator.TAG,
            "IOException while trying to write file for sharing: " + e.message
        )
    }
    return uri
}


fun saveImage(context:Context,bitmap: Bitmap)
{

    val filename = "${System.currentTimeMillis()}.jpg"
    var fos: OutputStream? = null

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        context.contentResolver?.also { resolver ->

            //Content resolver will process the contentvalues
            val contentValues = ContentValues().apply {
                //putting file information in content values
                put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }

            //Inserting the contentValues to contentResolver and getting the Uri
            val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

            //Opening an outputstream with the Uri that we got
            fos = imageUri?.let { resolver.openOutputStream(it) }
        }
    } else {
        //These for devices running on android < Q
        //So I don't think an explanation is needed here

        val image =    createImageFile( Environment.getExternalStorageDirectory().absolutePath + "/" + context.getString(R.string.app_name) + "/")
        fos = FileOutputStream(image)


    }

    fos?.use {
        //Finally writing the bitmap to the output stream that we opened
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
        showToast(context,context.getString(R.string.image_saved))

    }
}

@Throws(IOException::class)
fun from(context: Context, uri: Uri?): File? {
    val inputStream = context.contentResolver.openInputStream(uri!!)
    val fileName: String? = getFileName(context, uri)
    val splitName: Array<String> = splitFileName(fileName!!)
    var tempFile = File.createTempFile(splitName[0], splitName[1])
    tempFile = rename(tempFile, fileName)
    tempFile.deleteOnExit()
    var out: FileOutputStream? = null
    try {
        out = FileOutputStream(tempFile)
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    }
    if (inputStream != null) {
        out?.let { copy(inputStream, it) }
        inputStream.close()
    }
    out?.close()
    return tempFile
}

fun splitFileName(fileName: String): Array<String> {
    var name = fileName
    var extension = ""
    val i = fileName.lastIndexOf(".")
    if (i != -1) {
        name = fileName.substring(0, i)
        extension = fileName.substring(i)
    }
    return arrayOf(name, extension)
}

fun getFileName(context: Context, uri: Uri): String? {
    var result: String? = null
    if (uri.scheme == "content") {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        try {
            if (cursor != null && cursor.moveToFirst()) {
                result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor?.close()
        }
    }
    if (result == null) {
        result = uri.path
        val cut = result!!.lastIndexOf(File.separator)
        if (cut != -1) {
            result = result.substring(cut + 1)
        }
    }
    return result
}

fun rename(file: File, newName: String): File? {
    val newFile = File(file.parent, newName)
    if (newFile != file) {
        if (newFile.exists()) {
            if (newFile.delete()) {
                Log.d("FileUtil", "Delete old $newName file")
            }
        }
        if (file.renameTo(newFile)) {
            Log.d("FileUtil", "Rename file to $newName")
        }
    }
    return newFile
}

@Throws(IOException::class)
fun copy(input: InputStream, output: OutputStream): Int {
    val count = copyLarge(input, output)
    return if (count > Int.MAX_VALUE) {
        -1
    } else count.toInt()
}

@Throws(IOException::class)
fun copyLarge(input: InputStream, output: OutputStream): Long {
    return copyLarge(input, output, ByteArray(DEFAULT_BUFFER_SIZE))
}

@Throws(IOException::class)
fun copyLarge(input: InputStream, output: OutputStream, buffer: ByteArray?): Long {
    var count: Long = 0
    var n: Int
    while (EOF != input.read(buffer).also { n = it }) {
        output.write(buffer, 0, n)
        count += n.toLong()
    }
    return count
}

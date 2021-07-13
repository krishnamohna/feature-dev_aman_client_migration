package com.cardio.doctor.ui.base.activity

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.cardio.doctor.listeners.DialogHelper
import com.cardio.doctor.listeners.DialogProvider
import com.cardio.doctor.ui.base.viewmodel.BaseViewModel
import com.cardio.doctor.utils.DialogHelperImpl

abstract class BaseActivity : AppCompatActivity() , DialogProvider {
    protected val baseViewModel : BaseViewModel by viewModels()

    private val dialogHelperImps by lazy {
        DialogHelperImpl(this)
    }

    override fun provideDialogHelper(): DialogHelper {
        return dialogHelperImps
    }

    inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
        crossinline bindingInflater: (LayoutInflater) -> T) =
        lazy(LazyThreadSafetyMode.NONE) {
            bindingInflater.invoke(layoutInflater)
        }

}
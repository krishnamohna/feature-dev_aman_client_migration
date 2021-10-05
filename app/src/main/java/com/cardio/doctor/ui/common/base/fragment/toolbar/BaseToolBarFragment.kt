package com.cardio.doctor.ui.common.base.fragment.toolbar

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.cardio.doctor.ui.common.base.fragment.BaseFragment

abstract class BaseToolBarFragment<Binding : ViewBinding> : BaseFragment<Binding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getToolbarImp()?.let {
            setUpToolbar(it)
        }
    }

    private fun setUpToolbar(view: IToolbar) {
        view?.setToolbar()
    }

    abstract fun getToolbarImp(): IToolbar?

}
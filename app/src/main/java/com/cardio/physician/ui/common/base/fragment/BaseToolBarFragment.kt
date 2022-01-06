package com.cardio.physician.ui.common.base.fragment

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar

abstract class BaseToolBarFragment<Binding : ViewBinding> : BaseFragment<Binding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getToolbarImp()?.let {
            setUpToolbar(it)
        }
    }

    private fun setUpToolbar(view: IToolbar) {
        view?.setToolbar()
    }

    abstract fun getToolbarImp(): IToolbar?

}
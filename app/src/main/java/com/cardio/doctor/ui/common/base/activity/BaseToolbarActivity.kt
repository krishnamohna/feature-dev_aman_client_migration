package com.cardio.doctor.ui.common.base.activity

import android.os.Bundle
import com.cardio.doctor.ui.common.base.fragment.toolbar.IToolbar

abstract class BaseToolbarActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getToolbarImp()?.let {
            setUpToolbar(it)
        }
    }

    abstract fun getToolbarImp(): IToolbar

    fun setUpToolbar(
        view: IToolbar
    ) {
        view.setToolbar()
    }
}
package com.cardio.physician.ui.common.base.activity

import android.os.Bundle
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar

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
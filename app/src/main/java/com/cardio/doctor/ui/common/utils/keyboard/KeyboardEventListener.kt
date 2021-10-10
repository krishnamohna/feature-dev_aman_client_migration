package com.cardio.doctor.ui.common.utils.keyboard

import android.view.ViewTreeObserver
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class KeyboardEventListener(private val rootView: Int,
        private val activity: AppCompatActivity,
        private val callback: (isOpen: Boolean) -> Unit
) : LifecycleObserver {

    private val listener = object : ViewTreeObserver.OnGlobalLayoutListener {
        private var lastState: Boolean = activity.isKeyboardOpen(rootView)

        override fun onGlobalLayout() {
            val isOpen = activity.isKeyboardOpen(rootView)
            if (isOpen == lastState) {
                return
            } else {
                dispatchKeyboardEvent(isOpen)
                lastState = isOpen
            }
        }
    }

    init {
        // Dispatch the current state of the keyboard
        dispatchKeyboardEvent(activity.isKeyboardOpen(rootView))
        // Make the component lifecycle aware
        activity.lifecycle.addObserver(this)
        registerKeyboardListener()
    }

    private fun registerKeyboardListener() {
        activity.getRootView(rootView).viewTreeObserver.addOnGlobalLayoutListener(listener)
    }

    private fun dispatchKeyboardEvent(isOpen: Boolean) {
        when {
            isOpen  -> callback(true)
            !isOpen -> callback(false)
        }
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_PAUSE)
    @CallSuper
    fun onLifecyclePause() {
        unregisterKeyboardListener()
    }

    private fun unregisterKeyboardListener() {
        activity.getRootView(rootView).viewTreeObserver.removeOnGlobalLayoutListener(listener)
    }
}
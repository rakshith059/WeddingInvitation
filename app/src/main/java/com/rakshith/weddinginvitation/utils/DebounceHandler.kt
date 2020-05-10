package com.rakshith.weddinginvitation.utils

import android.os.Handler
import android.os.Looper

/**
 * This object is to avoid multiple click event on the same view.
 * Avoids multiple calls
 */
object DebounceHandler {
    private val mHandler = Handler(Looper.getMainLooper())
    fun handle(runnable: Runnable, delay: Long = 280L) {
        mHandler.removeCallbacksAndMessages(null)
        mHandler.postDelayed(runnable, delay)
    }
}
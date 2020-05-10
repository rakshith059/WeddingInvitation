package com.rakshith.weddinginvitation

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class MainApplication : Application() {
    override fun onCreate() {
        Fresco.initialize(this)
        super.onCreate()
    }
}
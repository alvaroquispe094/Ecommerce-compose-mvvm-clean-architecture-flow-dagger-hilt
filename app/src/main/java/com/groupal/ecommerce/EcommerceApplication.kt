package com.groupal.ecommerce

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EcommerceApplication: Application() {

    // AppContainer instance used by the rest of classes to obtain dependencies
//    lateinit var container: AppContainer
//
//    override fun onCreate() {
//        super.onCreate()
//        container = AppContainerImpl(this)
//    }

}
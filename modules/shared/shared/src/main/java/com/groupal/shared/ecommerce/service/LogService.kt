package com.groupal.shared.ecommerce.service

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogService @Inject constructor() {

    fun verbose(tag: String, message: String): Int = Log.v(tag, message)

    fun error(tag: String, message: String, error : Throwable?): Int = Log.e(tag, message, error)

    fun debug(tag: String, message: String): Int = Log.d(tag, message)

    fun info(tag: String, message: String): Int = Log.i(tag, message)

    fun warning(tag: String, message: String): Int = Log.w(tag, message)

}
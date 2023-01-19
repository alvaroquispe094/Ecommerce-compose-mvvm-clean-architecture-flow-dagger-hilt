package com.groupal.shared.ecommerce.domain

import android.content.Context
import com.groupal.shared.R

object SharedValidation {
    fun emptyValidationMessage(context: Context): String {
        return context.getString(( R.string.empty_field)) + ". "
    }

    fun imageValidationMessage(context: Context): String {
        return context.getString(( R.string.format_url)) + ". "
    }
}
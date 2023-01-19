package com.groupal.user.ecommerce.domain

import android.content.Context
import com.groupal.user.R

object UserValidation {
    fun passwordAndLongValidationMessage(from: Int, to: Int, context: Context): String {
        return "Must have: " + context.getString(R.string.user_upper_case_letter) + ". " +
                context.getString(R.string.user_lower_case_letter) + ". " +
                context.getString(R.string.user_number) + ". " +
                context.getString(R.string.user_symbol) + ". " +
                from + " a " + to + "characters."

    }

    fun lettersAndLongValidationMessage(from: Int, to: Int, context: Context): String {
        return "Must have: " + context.getString(R.string.user_only_letters) + ". " +
                from + " to " + to + " characters."

    }

    fun emailValidationMessage(context: Context): String {
        return "Must have: " + context.getString(( R.string.user_only_letters)) + ". " +
                context.getString(R.string.user_only_numbers) + ". " +
                context.getString(R.string.user_format_email) + "."

    }

    fun phoneValidationMessage(context: Context): String {
        return context.getString(( R.string.user_format_phone)) + ". "
    }

    fun userExistValidationMessage(context: Context): String {
        return context.getString(( R.string.user_exist)) + "."

    }

    fun confirmPasswordValidationMessage(context: Context): String {
        return context.getString(R.string.user_confirmPassword) + "."

    }
}
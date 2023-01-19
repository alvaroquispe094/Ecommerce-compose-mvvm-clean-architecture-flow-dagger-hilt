package com.groupal.user.ecommerce.domain

data class User(
    val email: String,
    val id: String? = null,
    val fullName: String? = null,
    val gender: String? = null,
    val birthdate: String? = null,
    val phone: String? = null,
    val avatar: String? = null,
) {
    companion object {

        fun isEmailValid(email: String): Boolean {
            val regex = Regex("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+")
            return regex.matches(email)
        }

        fun isPasswordValid(password: String): Boolean {
            //1 Mayuscula, 1 Minuscula, 1 Numero, 1 Simbolo, de 8 a 10 caracteres
            val regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#\$!%*?&])[A-Za-z\\d@#\$!%*?&]{8,20}\$")
            return regex.matches(password)
        }

        fun isConfirmPasswordValid(confirmpassword:String, password: String):Boolean{
            return confirmpassword == password
        }

        fun isFirstnameValid(fistName: String): Boolean {
            val regex = Regex("[a-zA-Z\\-]{2,30}")
            return regex.matches(fistName)
        }

        fun isLastnameValid(lastname: String): Boolean {
            val regex = Regex("[a-zA-Z\\-]{2,30}") //TODO: Parametrizar longitudes
            return regex.matches(lastname)
        }

        fun isPhoneValid(phone: String): Boolean {
            val regex = Regex("^\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*\$")
            return regex.matches(phone)
        }

        fun isValidAvatar(avatar: String): Boolean {
            //Log.d("TEST AVATAR", avatar)
            val regex = Regex("(http(s?):)([/|.|\\w|\\s|-])[-a-zA-Z0-9@:%_\\+.~#?&//=]{2,256}\\.[a-z]{2,4}\\b(\\/[-a-zA-Z0-9@:%_\\+.~#?&//=]*)?(?:jpg|gif|png)")
            return regex.matches(avatar)
        }
    }
}
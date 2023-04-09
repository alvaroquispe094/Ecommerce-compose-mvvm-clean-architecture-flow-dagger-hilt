package com.groupal.user.ecommerce.domain

data class Login(
    val sessionToken: String,
    val onBoardingStage: Int = 0
) {

    override fun equals(other: Any?): Boolean = (other is Login) && onBoardingStage == other.onBoardingStage

    override fun hashCode(): Int = 31 * onBoardingStage.hashCode()
}

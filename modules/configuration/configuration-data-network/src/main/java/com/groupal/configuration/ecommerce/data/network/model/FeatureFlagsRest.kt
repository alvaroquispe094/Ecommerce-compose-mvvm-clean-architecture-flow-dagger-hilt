package com.groupal.configuration.ecommerce.data.network.model

import com.groupal.configuration.ecommerce.domain.FeatureFlags
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeatureFlagsRest(
    val signUpEnable: Boolean
) {
    fun toDomain() = FeatureFlags(
        isSignUpEnable = signUpEnable
    )
}
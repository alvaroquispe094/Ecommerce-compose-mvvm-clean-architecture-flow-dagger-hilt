package com.groupal.ecommerce.domain.model

import com.google.gson.annotations.SerializedName

class Category (
    @SerializedName("category") var category: String,
    @SerializedName("path") var path: String,
)
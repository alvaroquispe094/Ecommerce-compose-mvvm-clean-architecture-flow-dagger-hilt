package com.groupal.shared.ecommerce.presentation.util

import androidx.annotation.DimenRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp

@Composable
@ReadOnlyComposable
fun floatDimensionResource(@DimenRes id: Int) = dimensionResource(id = id).value

@Composable
@ReadOnlyComposable
fun spDimensionResource(@DimenRes id: Int) = dimensionResource(id = id).value.sp
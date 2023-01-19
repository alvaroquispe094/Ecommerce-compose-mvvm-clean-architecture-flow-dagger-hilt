package com.groupal.shared.ecommerce.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.res.dimensionResource
import com.groupal.shared.ecommerce.presentation.util.floatDimensionResource
import com.groupal.shared.ecommerce.presentation.util.spDimensionResource
import com.groupal.shared.presentation.R

val LocalTheme = compositionLocalOf { Theme() }

@Composable
fun DefaultThemeProvider(children: @Composable () -> Unit) {
    val theme =  Theme(
        cell = Theme.Cell(
            minWidth = dimensionResource(R.dimen.cell_min_width),
            thumbHeight = dimensionResource(R.dimen.cell_thumb_height),
            playIconSize = dimensionResource(R.dimen.cell_play_icon_size)
        ),
        padding = Theme.Scale(
            xxsmall = dimensionResource(R.dimen.padding_xxsmall),
            xsmall = dimensionResource(R.dimen.padding_xsmall),
            small = dimensionResource(R.dimen.padding_small),
            medium = dimensionResource(R.dimen.padding_medium),
            large = dimensionResource(R.dimen.padding_large),
            xlarge = dimensionResource(R.dimen.padding_xlarge),
            xxlarge = dimensionResource(R.dimen.padding_xxlarge)
        ),
        fontSize = Theme.Scale(
            xxsmall = spDimensionResource(R.dimen.fontsize_xxsmall),
            xsmall = spDimensionResource(R.dimen.fontsize_xsmall),
            small = spDimensionResource(R.dimen.fontsize_small),
            medium = spDimensionResource(R.dimen.fontsize_medium),
            large = spDimensionResource(R.dimen.fontsize_large),
            xlarge = spDimensionResource(R.dimen.fontsize_xlarge),
            xxlarge = spDimensionResource(R.dimen.fontsize_xlarge)
        ),
        button = Theme.Button(
            roundedCornerShape = Theme.Button.RoundedCornerShape(
                small = dimensionResource(R.dimen.btn_RoundedCornerShape_small),
                medium = dimensionResource(R.dimen.btn_RoundedCornerShape_medium)
            ),
            elevation = Theme.Button.Elevation(
                default = dimensionResource(R.dimen.btn_defaultElevation_medium),
                pressed = dimensionResource(R.dimen.btn_pressedElevation_medium),
                disabled = dimensionResource(R.dimen.btn_disabledElevation)
            )
        ),
        space = Theme.Scale(
            xxsmall = dimensionResource(R.dimen.space_height_xsmall),
            xsmall = dimensionResource(R.dimen.space_height_xsmall),
            small = dimensionResource(R.dimen.space_height_small),
            medium = dimensionResource(R.dimen.space_height_medium),
            large = dimensionResource(R.dimen.space_height_large),
            xlarge = dimensionResource(R.dimen.space_height_xlarge),
            xxlarge = dimensionResource(R.dimen.space_height_xxlarge)
        ),
        size = Theme.Scale(
            xxsmall = dimensionResource(R.dimen.size_small),
            xsmall = dimensionResource(R.dimen.size_small),
            small = dimensionResource(R.dimen.size_small),
            medium = dimensionResource(R.dimen.size_medium),
            large = dimensionResource(R.dimen.size_medium),
            xlarge = dimensionResource(R.dimen.size_medium),
            xxlarge = dimensionResource(R.dimen.size_medium)
        ),
        shadow = Theme.Scale(
            xxsmall = dimensionResource(R.dimen.shadow_medium),
            xsmall = dimensionResource(R.dimen.shadow_medium),
            small = dimensionResource(R.dimen.shadow_medium),
            medium = dimensionResource(R.dimen.shadow_medium),
            large = dimensionResource(R.dimen.shadow_medium),
            xlarge = dimensionResource(R.dimen.shadow_medium),
            xxlarge = dimensionResource(R.dimen.shadow_medium)
        ),
        requiredWidth = Theme.Scale(
            xxsmall = dimensionResource(R.dimen.requiredWidth_medium),
            xsmall = dimensionResource(R.dimen.requiredWidth_medium),
            small = dimensionResource(R.dimen.requiredWidth_medium),
            medium = dimensionResource(R.dimen.requiredWidth_medium),
            large = dimensionResource(R.dimen.requiredWidth_medium),
            xlarge = dimensionResource(R.dimen.requiredWidth_medium),
            xxlarge = dimensionResource(R.dimen.requiredWidth_medium)
        ),
        requiredHeight = Theme.Scale(
            xxsmall = dimensionResource(R.dimen.requiredHeight_medium),
            xsmall = dimensionResource(R.dimen.requiredHeight_medium),
            small = dimensionResource(R.dimen.requiredHeight_medium),
            medium = dimensionResource(R.dimen.requiredHeight_medium),
            large = dimensionResource(R.dimen.requiredHeight_medium),
            xlarge = dimensionResource(R.dimen.requiredHeight_medium),
            xxlarge = dimensionResource(R.dimen.requiredHeight_medium)
        ),
        thickness = Theme.Scale(
            xxsmall = dimensionResource(R.dimen.thickness_medium),
            xsmall = dimensionResource(R.dimen.thickness_medium),
            small = dimensionResource(R.dimen.thickness_medium),
            medium = dimensionResource(R.dimen.thickness_medium),
            large = dimensionResource(R.dimen.thickness_medium),
            xlarge = dimensionResource(R.dimen.thickness_medium),
            xxlarge = dimensionResource(R.dimen.thickness_medium)
        ),
        width = Theme.Scale(
            xxsmall = dimensionResource(R.dimen.width_medium),
            xsmall = dimensionResource(R.dimen.width_medium),
            small = dimensionResource(R.dimen.width_medium),
            medium = dimensionResource(R.dimen.width_medium),
            large = dimensionResource(R.dimen.width_medium),
            xlarge = dimensionResource(R.dimen.width_medium),
            xxlarge = dimensionResource(R.dimen.width_medium)
        ),
        height = Theme.Scale(
            xxsmall = dimensionResource(R.dimen.height_medium),
            xsmall = dimensionResource(R.dimen.height_medium),
            small = dimensionResource(R.dimen.height_medium),
            medium = dimensionResource(R.dimen.height_medium),
            large = dimensionResource(R.dimen.height_medium),
            xlarge = dimensionResource(R.dimen.height_medium),
            xxlarge = dimensionResource(R.dimen.height_medium)
        ),
        alpha = Theme.Scale(
            xxsmall = floatDimensionResource(R.dimen.alpha_small_f),
            xsmall = floatDimensionResource(R.dimen.alpha_small_f),
            small = floatDimensionResource(R.dimen.alpha_small_f),
            medium = floatDimensionResource(R.dimen.alpha_medium_f),
            large = floatDimensionResource(R.dimen.alpha_medium_f),
            xlarge = floatDimensionResource(R.dimen.alpha_medium_f),
            xxlarge = floatDimensionResource(R.dimen.alpha_medium_f)
        ),
        fillMaxWidth = Theme.Scale(
            xxsmall = floatDimensionResource(R.dimen.fillmaxwidth_medium_f),
            xsmall = floatDimensionResource(R.dimen.fillmaxwidth_medium_f),
            small = floatDimensionResource(R.dimen.fillmaxwidth_medium_f),
            medium = floatDimensionResource(R.dimen.fillmaxwidth_medium_f),
            large = floatDimensionResource(R.dimen.fillmaxwidth_medium_f),
            xlarge = floatDimensionResource(R.dimen.fillmaxwidth_medium_f),
            xxlarge = floatDimensionResource(R.dimen.fillmaxwidth_medium_f)
        ),
        fillMaxHeight = Theme.Scale(
            xxsmall = floatDimensionResource(R.dimen.fillmaxheight_small_f),
            xsmall = floatDimensionResource(R.dimen.fillmaxheight_small_f),
            small = floatDimensionResource(R.dimen.fillmaxheight_small_f),
            medium = floatDimensionResource(R.dimen.fillmaxheight_medium_f),
            large = floatDimensionResource(R.dimen.fillmaxheight_medium_f),
            xlarge = floatDimensionResource(R.dimen.fillmaxheight_medium_f),
            xxlarge = floatDimensionResource(R.dimen.fillmaxheight_medium_f)
        ),
    )

    CompositionLocalProvider(LocalTheme provides theme) {
        children()
    }
}
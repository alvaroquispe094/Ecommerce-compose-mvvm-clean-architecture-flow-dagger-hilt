package com.groupal.shared.ecommerce.presentation.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Theme(
    val padding: Scale<Dp> = Scale.NoneDp,
    val fontSize: Scale<TextUnit> = Scale.NoneTextUnit,
    val space: Scale<Dp> = Scale.NoneDp,
    val size: Scale<Dp> = Scale.NoneDp,
    val shadow: Scale<Dp> = Scale.NoneDp,
    // TODO: Odd
    val requiredWidth: Scale<Dp> = Scale.NoneDp,
    // TODO: Odd
    val requiredHeight: Scale<Dp> = Scale.NoneDp,
    val thickness: Scale<Dp> = Scale.NoneDp,
    // TODO: Odd
    val width: Scale<Dp> = Scale.NoneDp,
    // TODO: Odd
    val height: Scale<Dp> = Scale.NoneDp,
    val alpha: Scale<Float> = Scale.NoneFloat,
    // TODO: Odd
    val fillMaxWidth: Scale<Float> = Scale.NoneFloat,
    // TODO: Odd
    val fillMaxHeight: Scale<Float> = Scale.NoneFloat,
    val button: Button = Button.None,
    val cell: Cell = Cell.None,
) {
    data class Button(
        val roundedCornerShape: RoundedCornerShape,
        val elevation: Elevation
    ) {
        data class RoundedCornerShape(
            val small: Dp,
            val medium: Dp
        ) {
            companion object {
                val None = RoundedCornerShape(0.dp, 0.dp)
            }
        }

        data class Elevation(
            val default: Dp,
            val pressed: Dp,
            val disabled: Dp
        ) {
            companion object {
                val None = Elevation(0.dp, 0.dp, 0.dp)
            }
        }

        companion object {
            val None = Button(RoundedCornerShape.None, Elevation.None)
        }
    }

    data class Cell(
        val minWidth: Dp,
        val thumbHeight: Dp,
        val playIconSize: Dp
    ) {
        companion object {
            val None = Cell(0.dp, 0.dp, 0.dp)
        }
    }

    data class Scale<Dimension>(
        val xxsmall: Dimension,
        val xsmall: Dimension,
        val small: Dimension,
        val medium: Dimension,
        val large: Dimension,
        val xlarge: Dimension,
        val xxlarge: Dimension
    ) {
        companion object {
            val NoneDp = Scale(0.dp, 0.dp, 0.dp, 0.dp, 0.dp, 0.dp, 0.dp)
            val NoneTextUnit = Scale(0.sp, 0.sp, 0.sp, 0.sp, 0.sp, 0.sp, 0.sp)
            val NoneFloat = Scale(0f, 0f, 0f, 0f, 0f, 0f, 0f)
        }
    }
}
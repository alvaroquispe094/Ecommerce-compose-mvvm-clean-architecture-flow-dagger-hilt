package com.groupal.shared.ecommerce.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.groupal.shared.presentation.R

@Composable
fun SnackBar(
    snackbarHostState: SnackbarHostState,
    color: Color, // 'success', 'error'
){

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = dimensionResource(id = R.dimen.padding_medium), horizontal = dimensionResource(id = R.dimen.padding_medium)),
        constraintSet = snackConstraintSet()
    ) {
        SnackbarHost(
            modifier = Modifier.layoutId("snackBarHost"),
            hostState = snackbarHostState,
            snackbar = {
                Snackbar(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_xxsmall)),
                    action = {
                        TextButton(
                            contentPadding =  PaddingValues(),
                            modifier = Modifier
                                .width(dimensionResource(id = R.dimen.padding_xlarge)),
                            onClick = {
                                snackbarHostState.currentSnackbarData?.dismiss()
                            }
                        ){
                            Text(
                                text = snackbarHostState.currentSnackbarData?.actionLabel?: "x",
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = 24.sp,
                                )
                            )
                        }
                    },
                    backgroundColor = color,
                ) {
                    Text(snackbarHostState.currentSnackbarData?.message?: "")
                }
            }
        )
    }
}

fun snackConstraintSet() = ConstraintSet {

    val snackBarHost = createRefFor("snackBarHost")
    constrain(snackBarHost) {
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }
}
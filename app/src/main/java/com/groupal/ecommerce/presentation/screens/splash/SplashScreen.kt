package com.groupal.ecommerce.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.groupal.ecommerce.R
import com.groupal.ecommerce.presentation.viewmodel.SplashScreenViewModel
import com.groupal.shared.ecommerce.presentation.theme.DefaultThemeProvider
import com.groupal.shared.ecommerce.presentation.util.collectAsStateLifecycleAware

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel = viewModel(),
    nextScreen: () -> Unit
) {
    val isLoadingState by viewModel.isLoading.collectAsStateLifecycleAware()

    if (isLoadingState.not()) {
        nextScreen()
    }

    SplashContent()
}

@Composable
fun SplashContent() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.splash_background),
            contentDescription = ""
        )

        Image(
            modifier = Modifier.align(alignment = Alignment.Center),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_logo_dark),
            contentDescription = ""
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SplashPreview() {
    DefaultThemeProvider {
        SplashContent()
    }
}
package com.groupal.ecommerce.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.groupal.ecommerce.di.ApplicationModule
import com.groupal.ecommerce.presentation.navigation.AppNavigation
import com.groupal.ecommerce.presentation.theme.EcommerceTheme
import com.groupal.ecommerce.presentation.viewmodel.SplashScreenViewModel
import com.groupal.shared.ecommerce.presentation.theme.DefaultThemeProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApplicationModule.setApplicationScope(this.lifecycleScope)

        // Splash screen
        installSplashScreen().apply {
            setKeepOnScreenCondition() {
                viewModel.isLoading.value
            }
        }
        setContent {
            MyApp {
                DefaultThemeProvider {
                    AppNavigation()
                }
            }
        }
    }

    @Composable
    fun MyApp(content: @Composable () -> Unit) {
        EcommerceTheme {
            Surface(color = MaterialTheme.colors.background) {
                content()
            }
        }
    }

}

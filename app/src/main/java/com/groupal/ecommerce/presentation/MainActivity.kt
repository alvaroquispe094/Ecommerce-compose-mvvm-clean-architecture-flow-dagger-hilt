package com.groupal.ecommerce.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import com.groupal.ecommerce.di.ApplicationModule
import com.groupal.ecommerce.presentation.navigation.AppNavigation
import com.groupal.ecommerce.presentation.theme.EcommerceTheme
import com.groupal.shared.ecommerce.presentation.theme.DefaultThemeProvider
import com.groupal.user.ecommerce.data.service.UserDataModuleConfigurationService
import com.groupal.user.ecommerce.service.AuthService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var authService: AuthService

    @Inject lateinit var userDataModuleConfigurationService: UserDataModuleConfigurationService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApplicationModule.setApplicationScope(this.lifecycleScope)

        authService.initialize(this.lifecycleScope)

        userDataModuleConfigurationService.configure(this.lifecycleScope)
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
            //Surface(color = MaterialTheme.colors.background) {
                content()
            //}
        }
    }

}

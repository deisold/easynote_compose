package com.dirkeisold.easynotecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dirkeisold.easynotecompose.MainViewModel.MainUiState
import com.dirkeisold.easynotecompose.MainViewModel.MainUiState.Data
import com.dirkeisold.easynotecompose.MainViewModel.MainUiState.Loading
import com.dirkeisold.easynotecompose.core.model.ThemeConfig
import com.dirkeisold.easynotecompose.design.theme.MyTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var uiState: MainUiState by mutableStateOf(Loading)

        WindowCompat.setDecorFitsSystemWindows(window, true)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.onEach { uiState = it }.collect()
            }
        }

        splashScreen.setKeepOnScreenCondition {
            when (uiState) {
                Loading -> true
                is Data -> false
            }
        }

        setContent {
            val useDarkScheme = useDarkTheme(uiState)

            MyTheme(darkTheme = useDarkScheme) {
                MyApp()
            }
        }
    }
}

@Composable
internal fun useDarkTheme(uiState: MainUiState): Boolean = when (uiState) {
    Loading -> isSystemInDarkTheme()
    is Data -> when (uiState.userData.themeConfig) {
        ThemeConfig.FollowSystem -> isSystemInDarkTheme()
        ThemeConfig.Light -> false
        ThemeConfig.Dark -> true
    }
}
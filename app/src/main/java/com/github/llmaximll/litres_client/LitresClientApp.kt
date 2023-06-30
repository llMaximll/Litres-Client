package com.github.llmaximll.litres_client

import android.content.res.Resources
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.llmaximll.litres_client.common.snackbar.SnackbarManager
import com.github.llmaximll.litres_client.screens.auth.AuthScreen
import com.github.llmaximll.litres_client.screens.splash.SplashScreen
import com.github.llmaximll.litres_client.ui.theme.Litres_ClientTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun LitresClientApp() {
    Litres_ClientTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val appState = rememberAppState()

            Scaffold(
                snackbarHost = {
                    SnackbarHost(
                        hostState = appState.snackbarHostState,
                        snackbar = { snackbarData ->
                            Snackbar(snackbarData, contentColor = MaterialTheme.colorScheme.onPrimary)
                        },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            ) { innerPaddingModifier ->
                NavHost(
                    navController = appState.navController,
                    startDestination = SPLASH_SCREEN,
                    modifier = Modifier.padding(innerPaddingModifier)
                ) {
                    litresClientGraph(appState)
                }
            }
        }
    }
}

@Composable
fun rememberAppState(
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(snackbarHostState, navController, snackbarManager, resources, coroutineScope) {
        LitresClientAppState(snackbarHostState, navController, snackbarManager, resources, coroutineScope)
    }

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

fun NavGraphBuilder.litresClientGraph(appState: LitresClientAppState) {
    composable(SPLASH_SCREEN) {
        EnterAnimation {
            SplashScreen(popUpAndNavigateToScreen = { popUp, route -> appState.popUpAndNavigate(popUp, route) })
        }
    }
    composable(AUTH_SCREEN) {
        EnterAnimation {
            AuthScreen(navigateToScreen = { route -> appState.navigate(route) })
        }
    }
    composable(MAIN_SCREEN) {
        EnterAnimation {
            AuthScreen(navigateToScreen = { route -> appState.navigate(route) })
        }
    }
}

@Composable
fun EnterAnimation(content: @Composable AnimatedVisibilityScope.() -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = slideInHorizontally() + fadeIn(),
        exit = slideOutHorizontally() + fadeOut(),
        content = content
    )
}
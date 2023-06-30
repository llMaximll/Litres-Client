package com.github.llmaximll.litres_client.screens.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.github.llmaximll.litres_client.AUTH_SCREEN
import com.github.llmaximll.litres_client.MAIN_SCREEN
import com.github.llmaximll.litres_client.SPLASH_SCREEN
import com.github.llmaximll.litres_client.common.ext.getError
import com.github.llmaximll.litres_client.common.ext.launchCatching
import com.github.llmaximll.litres_client.model.EncryptedSharedPreferences
import com.github.llmaximll.litres_client.model.service.data_requests.ActualizeSidRequest
import com.github.llmaximll.litres_client.model.service.data_requests.BaseRequest
import com.github.llmaximll.litres_client.model.service.remote_source.auth.AuthRemoteSource
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val encryptedSharedPreferences: EncryptedSharedPreferences,
    private val authRemoteSource: AuthRemoteSource
) : ViewModel() {
    val uiState = mutableStateOf(SplashUiState())

    suspend fun onAppStart(
        popUpAndNavigateToScreen: (String, String) -> Unit
    ) {
        launchCatching(
            onError = {
                uiState.value = uiState.value.copy(isLoading = false)
                popUpAndNavigateToScreen.invoke(SPLASH_SCREEN, AUTH_SCREEN)
            }
        ) {
            uiState.value = uiState.value.copy(isLoading = true)

//            val sid = encryptedSharedPreferences.sharedPreferences.getString(ESP_SID, null)

            val sid = "78838d3c7fbf640a4c52956569bef3c685"

            if (/*sid.isNullOrBlank()*/ false) { // TODO
                popUpAndNavigateToScreen.invoke(SPLASH_SCREEN, AUTH_SCREEN)
            } else {
                val response = authRemoteSource.actualizeSid(
                    BaseRequest(
                        sid = sid,
                        requests = listOf(ActualizeSidRequest())
                    )
                )

                val error = response.getError()

                if (error.success) {
                    // TODO Записать значение sid
                    Timber.e("Main")
                    popUpAndNavigateToScreen.invoke(SPLASH_SCREEN, MAIN_SCREEN)
                } else {
                    Timber.e("Auth")
                    popUpAndNavigateToScreen.invoke(SPLASH_SCREEN, AUTH_SCREEN)
                }
            }

            uiState.value = uiState.value.copy(isLoading = false)
        }
    }
}
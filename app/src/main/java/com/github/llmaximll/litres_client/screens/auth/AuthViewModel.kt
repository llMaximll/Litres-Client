package com.github.llmaximll.litres_client.screens.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.github.llmaximll.litres_client.MAIN_SCREEN
import com.github.llmaximll.litres_client.common.ext.getError
import com.github.llmaximll.litres_client.R.string as AppText
import com.github.llmaximll.litres_client.common.ext.launchCatching
import com.github.llmaximll.litres_client.common.snackbar.SnackbarManager
import com.github.llmaximll.litres_client.model.EncryptedSharedPreferences
import com.github.llmaximll.litres_client.model.service.data_requests.ActualizeSidRequest
import com.github.llmaximll.litres_client.model.service.data_requests.BaseRequest
import com.github.llmaximll.litres_client.model.service.data_requests.CreateSidRequest
import com.github.llmaximll.litres_client.model.service.data_responses.ErrorType.Companion.getErrorType
import com.github.llmaximll.litres_client.model.service.remote_source.auth.AuthRemoteSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRemoteSource: AuthRemoteSource,
    private val encryptedSharedPreferences: EncryptedSharedPreferences
) : ViewModel() {
    val uiState = mutableStateOf(AuthUiState())

    fun onLoginChange(newValue: String) {
        uiState.value = uiState.value.copy(login = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onAuthButton(
        navigateToScreen: (String) -> Unit
    ) {
        launchCatching(
            onError = {
                uiState.value = uiState.value.copy(isLoading = false)
            }
        ) {
            uiState.value = uiState.value.copy(isLoading = true)

            if (uiState.value.login.isBlank()) {
                uiState.value = uiState.value.copy(isLoading = false)
                SnackbarManager.showMessage(AppText.auth_error_login_empty)
                return@launchCatching
            }

            if (uiState.value.password.isBlank()) {
                uiState.value = uiState.value.copy(isLoading = false)
                SnackbarManager.showMessage(AppText.auth_error_password_empty)
                return@launchCatching
            }

            val request = BaseRequest(
                requests = listOf(CreateSidRequest(
                    param = CreateSidRequest.Param(
                        login = uiState.value.login,
                        pwd = uiState.value.password,
                        sid = encryptedSharedPreferences.sid
                    )
                ))
            )
            val response = authRemoteSource.createSid(request)

            val error = response.getError().getErrorType()

            val logMeInBaby = response.body()?.logMeInBaby

            if (logMeInBaby?.success == true) {
                navigateToScreen.invoke(MAIN_SCREEN)
            } else {
                SnackbarManager.showMessage(error.localMessage)
            }
        }
    }
}
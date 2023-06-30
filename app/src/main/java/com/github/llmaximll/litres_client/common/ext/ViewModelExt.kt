package com.github.llmaximll.litres_client.common.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.llmaximll.litres_client.common.snackbar.SnackbarManager
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import com.github.llmaximll.litres_client.R.string as AppText

fun ViewModel.launchCatching(
    snackbar: Boolean = true,
    onError: () -> Unit = { },
    block: suspend CoroutineScope.() -> Unit
) =
    this.viewModelScope.launch(
        CoroutineExceptionHandler { _, throwable ->
            if (snackbar) {
                SnackbarManager.showMessage(AppText.common_error)
            }

            Timber.e("launchCatching:: throwable:${throwable.message}")

            onError.invoke()
        },
        block = block
    )
package com.github.llmaximll.litres_client.common.snackbar

import android.content.res.Resources
import androidx.annotation.StringRes
import com.github.llmaximll.litres_client.R.string as AppText

sealed class SnackbarMessage {

    class StringSnackbar(val message: String) : SnackbarMessage()

    class ResourceSnackbar(@StringRes val message: Int) : SnackbarMessage()

    companion object {

        fun SnackbarMessage.toMessage(resources: Resources): String =
            when (this) {
                is StringSnackbar -> this.message
                is ResourceSnackbar -> resources.getString(this.message)
            }

        fun Throwable.toSnackbarMessage(): SnackbarMessage {
            val message = this.message.orEmpty()
            return if (message.isNotBlank()) StringSnackbar(message)
            else ResourceSnackbar(AppText.common_error)
        }
    }
}
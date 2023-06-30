package com.github.llmaximll.litres_client.model.service.data_responses

import androidx.annotation.StringRes
import com.github.llmaximll.litres_client.R.string as AppText
import com.google.gson.annotations.SerializedName

data class Error(
    val success: Boolean = false,
    @SerializedName("error_message")
    val message: String = "",
    @SerializedName("error_code")
    val code: Int = 0,
    val time: String = ""
)

enum class ErrorType(
    val code: Int,
    val message: String,
    @StringRes val localMessage: Int
) {
    UNDEFINED(code = 0, message = "undefined", localMessage = AppText.error_undefined),
    AUTH_INVALID_SID(code = 101000, message = "sid invalid", localMessage = AppText.error_auth_invalid_sid),
    AUTH_INVALID_LOGIN_PASS(code = 101013, message = "Неверное сочетание логина/пароля", localMessage = AppText.error_auth_invalid_sid),
    AUTH_LOT_MISTAKES(code = 101014, message = "Слишком много ошибок авторизации, доступ временно заблокирован", localMessage = AppText.error_auth_lot_mistakes),
    AUTH_USER_BLOCKED(code = 101051, message = "Авторизация запрещена", localMessage = AppText.error_auth_user_blocked),
    AUTH_FORBIDDEN_LIBRARY(code = 101051, message = "В этом приложении запрещено авторизовываться под учётной записью библиотекаря", localMessage = AppText.error_auth_forbidden_library);

    companion object {
        fun Error.getErrorType(): ErrorType =
            ErrorType.values().find { type -> type.code == this.code }
                ?: UNDEFINED
    }
}
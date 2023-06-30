package com.github.llmaximll.litres_client.model.service.data_responses

import java.util.Date

abstract class Response

data class CreateSidResponse(
    val success: Boolean = false,
    val time: Date = Date(),
    val logMeInBaby: LogInMeBaby
) : Response() {
    data class LogInMeBaby(
        val success: Boolean = false,
        val sid: String = "",
        val country: String = "RUS"
    )
}
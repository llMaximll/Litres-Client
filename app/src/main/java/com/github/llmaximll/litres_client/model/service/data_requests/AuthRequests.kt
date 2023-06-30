package com.github.llmaximll.litres_client.model.service.data_requests

abstract class Request

data class ActualizeSidRequest(
    val func: String = "w_actualize_sid",
    val id: String = "update_my_sid_plz"
) : Request()

data class CreateSidRequest(
    val func: String = "w_create_sid",
    val id: String = "log_me_in_baby",
    val param: Param
) : Request() {
    data class Param(
        val login: String,
        val pwd: String,
        val sid: String
    )
}
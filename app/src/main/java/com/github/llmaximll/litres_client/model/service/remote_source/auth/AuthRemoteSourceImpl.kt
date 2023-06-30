package com.github.llmaximll.litres_client.model.service.remote_source.auth

import com.github.llmaximll.litres_client.model.service.AuthService
import com.github.llmaximll.litres_client.model.service.data_requests.ActualizeSidRequest
import com.github.llmaximll.litres_client.model.service.data_requests.BaseRequest
import com.github.llmaximll.litres_client.model.service.data_requests.CreateSidRequest
import com.github.llmaximll.litres_client.model.service.data_responses.CreateSidResponse
import com.google.gson.Gson
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class AuthRemoteSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthRemoteSource {

    override suspend fun actualizeSid(params: BaseRequest<ActualizeSidRequest>): Response<Any> {
        val paramsJson = Gson().toJson(params)
        Timber.v("actualizeSid:: paramsJson:$paramsJson")
        return authService.actualizeSid(paramsJson)
    }

    override suspend fun createSid(params: BaseRequest<CreateSidRequest>): Response<CreateSidResponse> {
        val paramsJson = Gson().toJson(params)
        Timber.v("createSid:: paramsJson:$paramsJson")
        return authService.createSid(paramsJson)
    }
}
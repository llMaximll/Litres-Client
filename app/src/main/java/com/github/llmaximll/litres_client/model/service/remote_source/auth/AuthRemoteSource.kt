package com.github.llmaximll.litres_client.model.service.remote_source.auth

import com.github.llmaximll.litres_client.model.service.data_requests.ActualizeSidRequest
import com.github.llmaximll.litres_client.model.service.data_requests.BaseRequest
import com.github.llmaximll.litres_client.model.service.data_requests.CreateSidRequest
import com.github.llmaximll.litres_client.model.service.data_responses.CreateSidResponse
import retrofit2.Response

interface AuthRemoteSource {

    suspend fun actualizeSid(
        params: BaseRequest<ActualizeSidRequest>
    ): Response<Any>

    suspend fun createSid(
        params: BaseRequest<CreateSidRequest>
    ): Response<CreateSidResponse>
}
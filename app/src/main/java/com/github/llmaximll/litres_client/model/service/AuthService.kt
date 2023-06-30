package com.github.llmaximll.litres_client.model.service

import com.github.llmaximll.litres_client.model.service.data_requests.BaseRequest
import com.github.llmaximll.litres_client.model.service.data_requests.CreateSidRequest
import com.github.llmaximll.litres_client.model.service.data_responses.CreateSidResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {

    @FormUrlEncoded
    @POST("catalitv2")
    suspend fun actualizeSid(
        @Field("jdata", encoded = true) params: String
    ): Response<Any>

    @FormUrlEncoded
    @POST("catalitv2")
    suspend fun createSid(
        @Field("jdata", encoded = true) params: String
    ): Response<CreateSidResponse>
}
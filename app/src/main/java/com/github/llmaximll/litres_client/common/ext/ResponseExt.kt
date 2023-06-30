package com.github.llmaximll.litres_client.common.ext

import com.github.llmaximll.litres_client.model.service.data_responses.Error
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import retrofit2.Response

fun <T : Any> Response<T>.getError(): Error {
    val gson = GsonBuilder().create()

    val errorText = gson.toJson(this.body())

    return gson.fromJson(
        errorText,
        object : TypeToken<Error>() {}.type
    )
}
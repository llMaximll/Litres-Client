package com.github.llmaximll.litres_client.model.service.data_requests

import com.github.llmaximll.litres_client.common.ext.toIso8601
import com.github.llmaximll.litres_client.common.ext.toSha256
import java.util.Date

data class BaseRequest<T : Request>(
    val app: Int? = null,
    val time: String = Date().toIso8601(),
    val sha: String? = null,
    val sid: String = "",
    val requests: List<T>
)

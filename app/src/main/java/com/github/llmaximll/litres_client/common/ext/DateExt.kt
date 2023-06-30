package com.github.llmaximll.litres_client.common.ext

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Date.toIso8601(): String {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.getDefault())
    format.timeZone = TimeZone.getTimeZone("Etc/GMT-3")
    return format.format(this)
}
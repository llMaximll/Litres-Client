package com.github.llmaximll.litres_client.common.ext

import java.security.MessageDigest

private fun toHex(byteArray: ByteArray):String{
    val result = with(StringBuilder()){
        byteArray.forEach {
            //Convert to hexadecimal
            val value = it
            val hex = value.toInt() and (0xFF)
            //Convert into a hexadecimal string
            val hexStr = Integer.toHexString(hex)
            if(hexStr.length == 1){
                this.append("0").append(hexStr)
            }else{
                //this can be omitted
                append(hexStr)
            }
        }
        //return value
        this.toString()
    }

    return result
}

fun String.toSha256(): String {
    val digest = MessageDigest.getInstance("SHA-256")
    val result = digest.digest("${this}SECRET_KEY".toByteArray())
    return toHex(result)
}
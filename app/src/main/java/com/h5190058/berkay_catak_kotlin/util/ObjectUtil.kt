package com.h5190058.berkay_catak_kotlin.util

import com.google.gson.Gson

object ObjectUtil {
    fun <T> objectToString(objectData: T): String {
        val gson = Gson()
        return gson.toJson(objectData)
    }

    inline fun <reified T> jsonStringToObject(jsonString: String): T {
        val gson = Gson()
        return gson.fromJson(jsonString, T::class.java)
    }
}
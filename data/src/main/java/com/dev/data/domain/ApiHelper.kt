package com.dev.data.domain

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.interceptors.LogRequestAsCurlInterceptor
import com.github.kittinunf.fuel.core.interceptors.LogResponseInterceptor
import com.github.kittinunf.fuel.gson.gsonDeserializer
import com.github.kittinunf.fuel.gson.jsonBody

object ApiHelper {

    init {

        FuelManager.instance.addRequestInterceptor {
            LogRequestAsCurlInterceptor(it)
        }
        FuelManager.instance.addResponseInterceptor {
            LogResponseInterceptor(it)
        }
    }


    inline suspend fun <reified T : Any> getData(
        path: String,
        queryParam: List<Pair<String, String>>
    ): Triple<Boolean, T?, Error?> {
        val result = Fuel
            .get(path, queryParam)
            .responseObject<T>(gsonDeserializer())
        result.third.fold(
            success = {
                return Triple(true, it, null)
            },
            failure = {
                return Triple(false, null, Error(it.message, it.cause))
            }
        )
    }
    inline suspend fun <reified T : Any> getPostData(
        path: String,
        queryParam: List<Pair<String, String>>,
        bodyParams: Map<String, Any>
    ): Triple<Boolean, T?, Error?> {
        val result = Fuel
            .post(path, queryParam)
            .header(mapOf("Content-Type" to "application/json"))
            .jsonBody(bodyParams)
            .responseObject<T>(gsonDeserializer())
        result.third.fold(
            success = {
                return Triple(true, it, null)
            },
            failure = {
                return Triple(false, null, Error(it.message, it.cause))
            }
        )
    }
}



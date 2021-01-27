package com.jyotirmayg.mvvmsample.data.network

import com.jyotirmayg.mvvmsample.util.APIExceptions
import com.jyotirmayg.mvvmsample.util.print
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeAPIRequest {
    suspend fun <T : Any> safeAPIRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            val responseBody = response.body()!!
            print(responseBody)
            return responseBody
        } else {
            val error = response.errorBody()!!.toString()
            print(error)
            val message = StringBuilder()
            error.let {
                try {
                    message.append(JSONObject(error).getString("message"))
                    message.append("\n")
                } catch (e: JSONException) { }
            }
            message.append("Error code: ${response.code()}")
            throw APIExceptions(message.toString())
        }
    }
}
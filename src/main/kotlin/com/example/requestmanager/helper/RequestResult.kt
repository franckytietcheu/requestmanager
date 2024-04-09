package com.example.requestmanager.helper

class RequestResult<out T>(
    val data: T?,
    val status: Status,
    val message: String?,
    val details: Any? = null,
        ) {

    companion object {

        fun <T> error( message: String?, data : T? = null): RequestResult<T> {
            return RequestResult(data, Status.ERROR, message)
        }

        fun <T> success(data: T): RequestResult<T> {
            return RequestResult(data, Status.SUCCESS, "")
        }

    }

}
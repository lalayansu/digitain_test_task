package com.example.domain.base.error

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

data class AppError(
    val commonErrorCode: CommonErrorCode,
    val commonErrorMessage: String
)

fun Throwable.mapToAppError(): AppError {
    return when (this) {
        is NetworkException -> AppError(
            commonErrorCode = code,
            commonErrorMessage = message.orEmpty()
        )

        is BaseException -> AppError(
            commonErrorCode = extraErrorCode,
            commonErrorMessage = message.orEmpty()
        )

        else -> mapToCommonError()
    }
}

fun Throwable.mapToCommonError(): AppError {
    val code = when (this) {
        is NullPointerException -> CommonErrorCode.NullPointerException
        is IllegalStateException -> CommonErrorCode.IllegalStateException
        is IllegalArgumentException -> CommonErrorCode.IllegalArgumentException
        is ArrayIndexOutOfBoundsException -> CommonErrorCode.ArrayIndexOutOfBoundsException
        is HttpException -> CommonErrorCode.from(this.code())
        is SocketTimeoutException,
        is ConnectException,
        is UnknownHostException -> CommonErrorCode.NoNetwork

        else -> CommonErrorCode.Unknown
    }

    return AppError(
        commonErrorCode = code,
        commonErrorMessage = message.orEmpty()
    )
}

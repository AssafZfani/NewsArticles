package zfani.assaf.fido.data.repository

import zfani.assaf.fido.data.network.entity.responses.ErrorResponseApp

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    object SuccessWithoutResponse : ApiResult<Nothing>()
    data class Error(val error: ErrorResponseApp) : ApiResult<Nothing>()
}
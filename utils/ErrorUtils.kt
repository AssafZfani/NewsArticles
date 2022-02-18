package zfani.assaf.fido.utils

import zfani.assaf.fido.data.repository.ApiResult
import zfani.assaf.fido.R
import zfani.assaf.fido.data.network.entity.responses.ErrorResponseApp

object ErrorUtils {

    fun getInternetError() = ErrorResponseApp(
        internetError = true,
        title = R.string.error_no_internet_title,
        message = R.string.error_no_internet_message,
        button = R.string.error_no_internet_button
    ).let { ApiResult.Error(it) }

    fun getUnAuthorisedError() =
        ErrorResponseApp(
            sessionExpired = true,
            title = R.string.error_auth_title,
            message = R.string.error_auth_message,
            button = R.string.error_auth_button
        ).let { ApiResult.Error(it) }

    fun getDefaultError() = ErrorResponseApp().let { ApiResult.Error(it) }
}
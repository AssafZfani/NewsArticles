package zfani.assaf.fido.data.events.articles

import zfani.assaf.fido.R
import zfani.assaf.fido.data.network.entity.responses.Article
import zfani.assaf.fido.data.network.entity.responses.ErrorResponseApp

sealed class ArticlesEvent {

    object Loading : ArticlesEvent()

    data class SuccessArticles(val articles: List<Article>) : ArticlesEvent()

    object UncheckedError : ArticlesEvent() {
        fun getMessage() = R.string.error_unchecked
    }

    data class Error(val error: ErrorResponseApp) : ArticlesEvent()
}
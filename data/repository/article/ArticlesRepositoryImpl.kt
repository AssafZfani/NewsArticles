package zfani.assaf.fido.data.repository.article

import zfani.assaf.fido.data.events.articles.ArticlesEvent
import zfani.assaf.fido.data.repository.ApiResult
import zfani.assaf.fido.data.repository.BaseRepository
import zfani.assaf.fido.data.network.service.FidoRetrofitService

class ArticlesRepositoryImpl(
    private var fidoRetrofitService: FidoRetrofitService,
) : BaseRepository(), ArticlesRepository {

    override suspend fun getArticles() = with(getApiResult {
        fidoRetrofitService.getArticles("us", "ce4ebd39ca0b43bea6fa7dd13fd00786")
    }) {
        when (this) {
            is ApiResult.Success -> ArticlesEvent.SuccessArticles(data.articles)
            is ApiResult.SuccessWithoutResponse -> ArticlesEvent.UncheckedError
            is ApiResult.Error -> ArticlesEvent.Error(error)
        }
    }
}

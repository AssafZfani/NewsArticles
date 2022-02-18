package zfani.assaf.fido.data.repository.article

import zfani.assaf.fido.data.events.articles.ArticlesEvent

interface ArticlesRepository {
    suspend fun getArticles(): ArticlesEvent
}

package zfani.assaf.fido.data.network.entity.responses

data class ArticlesResponse(val status: String, val totalResults: Int, val articles: List<Article>)
package zfani.assaf.fido.data.network.service

import zfani.assaf.fido.Consts
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import zfani.assaf.fido.data.network.entity.responses.ArticlesResponse

interface FidoRetrofitService {

    @GET(Consts.DATA_SOURCE_URL)
    suspend fun getArticles(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Response<ArticlesResponse>
}
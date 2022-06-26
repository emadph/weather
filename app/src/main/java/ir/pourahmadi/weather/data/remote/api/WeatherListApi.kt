package ir.pourahmadi.weather.data.remote.api

import ir.pourahmadi.weather.data.remote.dto.Wearher.WearherLIstResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface WearherListApi {

    @GET
    suspend fun getWearherList(@Url url: String): Response<List<WearherLIstResponse>>

}
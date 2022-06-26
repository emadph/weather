package ir.pourahmadi.weather.domain.use_case

import android.content.Context
import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.domain.model.Wearher.WearherListModel
import ir.pourahmadi.weather.domain.repository.WearherRepository
import ir.pourahmadi.weather.utils.NetworkUtils
import kotlinx.coroutines.flow.Flow
import java.net.URI
import javax.inject.Inject

class WearherUseCase @Inject constructor(
    private val context: Context,
    private val repository: WearherRepository
) {
    suspend fun getWearherList(url: String, pageIndex: Int): Flow<BaseResult<List<WearherListModel>>> {
        return if (!NetworkUtils.isNetworkAvailable(context)) {
            repository.getWearherListOffline(increaseIndex(url, pageIndex))
        } else
            repository.getWearherList(increaseIndex(url, pageIndex))
    }

    private fun increaseIndex(url: String, pageIndex: Int): String {
        /* if (pageIndex == 1) return url*/

        val baseUrl = getBaseUrl(url)
        val newPageParam = "page=$pageIndex"
        return appendUri(baseUrl, newPageParam)
    }

    private fun appendUri(uri: String, appendQuery: String): String {
        val oldUri = URI(uri)
        var newQuery = oldUri.query
        if (newQuery == null) {
            newQuery = appendQuery
        } else {
            newQuery += "&$appendQuery"
        }
        return URI(
            oldUri.scheme, oldUri.authority,
            oldUri.path, newQuery, oldUri.fragment
        ).toString()
    }

    private fun getBaseUrl(url: String): String {
        val uri = URI(url)
        return URI(
            uri.scheme,
            uri.authority,
            uri.path,
            null,  // Ignore the query part of the input url
            uri.fragment
        ).toString()
    }

/*
    private fun getIndex(url: String): Int {
        val url = url.toHttpUrlOrNull()
        var target = 0
        if (url != null) {
            target = url.queryParameter("page")?.toInt()!!
        }
        return target
    }
*/
}
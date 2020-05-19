package com.example.gifviewer.viewmodel

import android.app.Activity
import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.tenor.android.core.network.ApiClient
import com.tenor.android.core.response.BaseError
import com.tenor.android.core.response.WeakRefCallback
import com.tenor.android.core.response.impl.GifsResponse
import com.tenor.android.core.response.impl.TrendingGifResponse

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    val trendingGifsResponse: MutableLiveData<TrendingGifResponse> = MutableLiveData()
    val searchResultsResponse: MutableLiveData<GifsResponse> = MutableLiveData()

    fun getTrendingGIFs(limit: Int, pos: String) {
        val call =
            ApiClient.getInstance(context).getTrending(ApiClient.getServiceIds(context), limit, pos)
        call.enqueue(object : WeakRefCallback<Activity, TrendingGifResponse?>(Activity()) {
            override fun success(p0: Activity, p1: TrendingGifResponse?) {
                if (p1 == null) {
                    Toast.makeText(getApplication(), BaseError().error, Toast.LENGTH_SHORT).show()
                }
                else {
                    trendingGifsResponse.postValue(p1)
                }
            }

            override fun failure(p0: Activity, p1: BaseError?) {
                if (p1 != null)
                    Toast.makeText(getApplication(), p1.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getSearchResults(q: String, limit: Int, pos: String) {
        val call = ApiClient.getInstance(context)
            .search(ApiClient.getServiceIds(context), q, limit, pos)
        call.enqueue(object : WeakRefCallback<Activity, GifsResponse?>(Activity()) {
            override fun success(p0: Activity, p1: GifsResponse?) {
                searchResultsResponse.postValue(p1)
            }

            override fun failure(p0: Activity, p1: BaseError?) {
                Toast.makeText(getApplication(), p1.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }



}
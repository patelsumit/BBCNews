package com.app.bbcnewsapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.bbcnewsapp.endpoint.EndPointInstance
import com.app.bbcnewsapp.endpoint.NewsService
import com.app.bbcnewsapp.models.NewsList
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private var newsListLiveData: MutableLiveData<NewsList> = MutableLiveData()
    fun getNewsObserval(): MutableLiveData<NewsList> {
        return newsListLiveData
    }

    fun webApiCall() {
        viewModelScope.launch {
            val serviceInstance =
                EndPointInstance.getEndPointInstance().create(NewsService::class.java)
            val response = serviceInstance.getHeadlines()
            newsListLiveData.postValue(response)
        }
    }
}
package ir.pourahmadi.weather.presentation.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.pourahmadi.weather.domain.model.Wearher.WearherListModel
import ir.pourahmadi.weather.domain.model.topic.TopicModel


open class MainShareViewModel : ViewModel() {

    val WearherModel = MutableLiveData<WearherListModel>()
    val topicModel = MutableLiveData<TopicModel>()

    fun setWearherModel(data: WearherListModel) {
        WearherModel.value = data
    }

    fun setTopicModel(data: TopicModel) {
        topicModel.value = data
    }


}
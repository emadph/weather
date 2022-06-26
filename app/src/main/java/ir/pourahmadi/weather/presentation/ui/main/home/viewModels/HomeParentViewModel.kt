package ir.pourahmadi.weather.presentation.ui.main.home.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.domain.common.error.ErrorEntity
import ir.pourahmadi.weather.domain.model.topic.TopicModel
import ir.pourahmadi.weather.domain.use_case.TopicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeParentViewModel @Inject constructor(
    private val homeUseCase: TopicUseCase
) : ViewModel() {
    private val state = MutableStateFlow<HomeParentState>(HomeParentState.Init)
    val mState: StateFlow<HomeParentState> get() = state

    init {
        getHomeTabs()
    }

    private fun setLoading() {
        state.value = HomeParentState.IsLoading(true)
    }

    private fun hideLoading() {
        state.value = HomeParentState.IsLoading(false)
    }

    private fun getHomeTabs() {
        viewModelScope.launch {
            homeUseCase.getHomeTabs(true)
                .onStart {
                    setLoading()
                }
                .collect { baseResult ->
                    hideLoading()
                    resultHandle(baseResult)
                }
        }

    }

    private fun resultHandle(result: BaseResult<List<TopicModel>>) {
        when (result) {
            is BaseResult.Success -> {
                state.value = HomeParentState.SuccessTopicList(result.value as List<TopicModel>)
            }
            is BaseResult.NetworkError -> state.value = handleError(result.error)
            is BaseResult.GeneralError -> state.value =
                HomeParentState.ShowResIdToast(result.redId!!)
        }
    }

    private fun handleError(errorEntity: ErrorEntity): HomeParentState {
        return when (errorEntity) {
            is ErrorEntity.FromServer -> HomeParentState.ShowToast(errorEntity.error!!.m)
            is ErrorEntity.AccessDenied -> HomeParentState.ShowResIdToast(errorEntity.redId)
            is ErrorEntity.Network -> HomeParentState.ShowResIdToast(errorEntity.redId)
            is ErrorEntity.NotFound -> HomeParentState.ShowResIdToast(errorEntity.redId)
            is ErrorEntity.ServiceUnavailable -> HomeParentState.ShowResIdToast(errorEntity.redId)
            is ErrorEntity.Unknown -> HomeParentState.ShowResIdToast(errorEntity.redId)
        }
    }

}

sealed class HomeParentState {
    object Init : HomeParentState()
    data class IsLoading(val isLoading: Boolean) : HomeParentState()
    data class ShowToast(val message: String) : HomeParentState()
    object Success : HomeParentState()
    data class SuccessTopicList(val mModel: List<TopicModel>) : HomeParentState()
    data class ShowResIdToast(val resId: Int) : HomeParentState()
}
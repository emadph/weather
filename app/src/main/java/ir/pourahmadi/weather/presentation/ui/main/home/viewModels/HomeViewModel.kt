package ir.pourahmadi.weather.presentation.ui.main.home.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.domain.common.error.ErrorEntity
import ir.pourahmadi.weather.domain.model.Wearher.WearherListModel
import ir.pourahmadi.weather.domain.use_case.WearherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val WearherUseCase: WearherUseCase
) : ViewModel() {
    private val state = MutableStateFlow<WearherState>(WearherState.Init)
    val mState: StateFlow<WearherState> get() = state

    var WearherPage = -1
    var WearherResponse: MutableList<WearherListModel>? = null


    private fun setLoading() {
        state.value = WearherState.IsLoading(true)
    }

    private fun hideLoading() {
        state.value = WearherState.IsLoading(false)
    }


    fun getWearherList(url: String) {
        WearherPage++
        viewModelScope.launch {
            WearherUseCase.getWearherList(url, WearherPage)
                .onStart {
                    setLoading()
                }
                .collect {
                    hideLoading()
                    resultHandle(it)
                }
        }

    }

    private fun resultHandle(result: BaseResult<List<WearherListModel>>) {
        when (result) {
            is BaseResult.Success -> {
                if (!result.value.isNullOrEmpty())
                    if (WearherResponse == null) {
                        WearherResponse = result.value as MutableList<WearherListModel>
                    } else {
                        val oldData = WearherResponse
                        val newData = result.value as MutableList<WearherListModel>
                        oldData?.addAll(newData)
                    }


                state.value = WearherState.SuccessWearherList((WearherResponse ?: result.value)!!)
            }
            is BaseResult.NetworkError -> state.value = handleError(result.error)
            is BaseResult.GeneralError -> state.value = WearherState.ShowResIdToast(result.redId)
        }
    }

    private fun handleError(errorEntity: ErrorEntity): WearherState {
        return when (errorEntity) {
            is ErrorEntity.FromServer -> WearherState.ShowToast(errorEntity.error!!.m)
            is ErrorEntity.AccessDenied -> WearherState.ShowResIdToast(errorEntity.redId)
            is ErrorEntity.Network -> WearherState.ShowResIdToast(errorEntity.redId)
            is ErrorEntity.NotFound -> WearherState.ShowResIdToast(errorEntity.redId)
            is ErrorEntity.ServiceUnavailable -> WearherState.ShowResIdToast(errorEntity.redId)
            is ErrorEntity.Unknown -> WearherState.ShowResIdToast(errorEntity.redId)
        }
    }
}

sealed class WearherState {
    object Init : WearherState()
    data class IsLoading(val isLoading: Boolean) : WearherState()
    data class ShowToast(val message: String) : WearherState()
    object Success : WearherState()
    data class SuccessWearherList(val mModel: List<WearherListModel>) : WearherState()
    data class ShowResIdToast(val resId: Int) : WearherState()
}
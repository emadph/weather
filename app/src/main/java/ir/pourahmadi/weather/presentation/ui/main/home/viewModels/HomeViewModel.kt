package ir.pourahmadi.weather.presentation.ui.main.home.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.domain.common.error.ErrorEntity
import ir.pourahmadi.weather.domain.model.weather.WeatherModel
import ir.pourahmadi.weather.domain.use_case.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.pourahmadi.weather.data.remote.dto.weather.WeatherRequest
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: WeatherUseCase
) : ViewModel() {
    private val state = MutableStateFlow<WeatherState>(WeatherState.Init)
    val mState: StateFlow<WeatherState> get() = state

    private var searchJob: Job? = null

    private fun setLoading() {
        state.value = WeatherState.IsLoading(true)
    }

    private fun hideLoading() {
        state.value = WeatherState.IsLoading(false)
    }


    fun getWeather(request: WeatherRequest) {
        viewModelScope.launch {
            useCase.getWeather(request)
                .onStart {
                    setLoading()
                }
                .collect {
                    hideLoading()
                    resultHandle(it)
                }
        }

    }
    fun getWeatherOffline() {
        viewModelScope.launch {
            useCase.getWeatherOffline()
                .onStart {
                    setLoading()
                }
                .collect {
                    hideLoading()
                    resultHandle(it)
                }
        }

    }

    private fun resultCheckCityNameHandle(result: BaseResult<Unit>) {
        when (result) {
            is BaseResult.Success -> state.value = WeatherState.Success
            is BaseResult.NetworkError -> state.value = handleError(result.error)
            is BaseResult.GeneralError -> state.value = WeatherState.ShowResIdToast(result.redId)
        }
    }

    private fun resultHandle(result: BaseResult<WeatherModel>) {
        when (result) {
            is BaseResult.Success -> {
                state.value = WeatherState.SuccessWeather(result.value)
            }
            is BaseResult.NetworkError -> state.value = handleError(result.error)
            is BaseResult.GeneralError -> state.value = WeatherState.ShowResIdToast(result.redId)
        }
    }
    private fun resultOfflineHandle(result: BaseResult<WeatherModel>) {
        when (result) {
            is BaseResult.Success -> {
                state.value = WeatherState.SuccessWeather(result.value)
            }
            is BaseResult.NetworkError -> state.value = handleError(result.error)
            is BaseResult.GeneralError -> state.value = WeatherState.ShowResIdToast(result.redId)
        }
    }

    private fun handleError(errorEntity: ErrorEntity): WeatherState {
        return when (errorEntity) {
            is ErrorEntity.FromServer -> WeatherState.ShowToast(errorEntity.error?.message.toString())
            is ErrorEntity.AccessDenied -> WeatherState.ShowResIdToast(errorEntity.redId)
            is ErrorEntity.Network -> WeatherState.ShowResIdToast(errorEntity.redId)
            is ErrorEntity.NotFound -> WeatherState.ShowResIdToast(errorEntity.redId)
            is ErrorEntity.ServiceUnavailable -> WeatherState.ShowResIdToast(errorEntity.redId)
            is ErrorEntity.Unknown -> WeatherState.ShowResIdToast(errorEntity.redId)
        }
    }

    fun checkCityName(
        cityName: String
    ) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(2000L)
            useCase.checkCityName(
                cityName = cityName
            )
                .onStart {
                    setLoading()
                }
                .collect { baseResult ->
                    hideLoading()
                    resultCheckCityNameHandle(baseResult)
                }
        }
    }

}

sealed class WeatherState {
    object Init : WeatherState()
    data class IsLoading(val isLoading: Boolean) : WeatherState()
    data class ShowToast(val message: String) : WeatherState()
    object Success : WeatherState()
    data class SuccessWeather(val mModel: WeatherModel?) : WeatherState()
    data class ShowResIdToast(val resId: Int) : WeatherState()
}
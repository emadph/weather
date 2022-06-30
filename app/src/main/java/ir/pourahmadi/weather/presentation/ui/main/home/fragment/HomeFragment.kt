package ir.pourahmadi.weather.presentation.ui.main.home.fragment

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ir.pourahmadi.weather.BuildConfig
import ir.pourahmadi.weather.R
import ir.pourahmadi.weather.data.remote.dto.weather.WeatherRequest
import ir.pourahmadi.weather.domain.model.weather.WeatherModel
import ir.pourahmadi.weather.presentation.common.*
import ir.pourahmadi.weather.presentation.ui.base.MainBaseFragment
import ir.pourahmadi.weather.presentation.ui.main.home.viewModels.HomeViewModel
import ir.pourahmadi.weather.presentation.ui.main.home.viewModels.WeatherState
import ir.pourahmadi.weather.utils.Network.NetworkChangeReceiver
import ir.pourahmadi.weather.utils.Network.OnNetworkListener
import ir.pourahmadi.weather.utils.getDrawable
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_custom_edittext.view.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.math.roundToInt

@AndroidEntryPoint
class HomeFragment : MainBaseFragment(R.layout.fragment_home), OnNetworkListener {

    private var isSearchCalled: Boolean = false
    private lateinit var networkReceiver: NetworkChangeReceiver
    private var isAdvanceSearchClick: Boolean = false
    val viewModel: HomeViewModel by viewModels()
    private lateinit var request: WeatherRequest

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        clicks()
        observe()
        getDataOfflineForFirstInit()
    }

    private fun init() {
        networkReceiver = NetworkChangeReceiver(this)
        edtCityName.setDrawableLeft(getDrawable(context!!, R.drawable.ic_search_location)!!)
    }


    private fun getDataOfflineForFirstInit() {
        viewModel.getWeatherOffline()
    }

    private fun searchWeatherByCityAndLocation() {
        hideKeyboard()
        cslTempDetail.gone()

        val cityName = edtCityName.getText().trim()
        val cityLat = edtCityLat.getText().trim()
        val cityLng = edtCityLng.getText().trim()

        if (cityName.isNotEmpty() || cityLat.isNotEmpty() || cityLng.isNotEmpty()) {
            if (isAdvanceSearchClick)
                request = WeatherRequest(cityLat, cityLng, "")
            else
                request = WeatherRequest("", "", cityName)

            viewModel.getWeather(request)
        }
    }


    private fun observe() {
        viewModel.mState
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }


    private fun clicks() {

        edtCityName.edtCustome.onDone {
            isSearchCalled = true
            val cityName = edtCityName.getText().trim()
            if (validate(cityName)) {
                checkCityName()
            }
        }

        cbAdvanceSearch.setOnCheckedChangeListener { _, isChecked ->
            isAdvanceSearchClick = isChecked
            if (isChecked) {
                crdAdcanceSearch.visible()
                edtCityName.disableEnableControls { isEnabled = false }
            } else {
                crdAdcanceSearch.gone()
                edtCityName.disableEnableControls { isEnabled = true }
            }
        }

        btnSearchWeather.setOnClickListener {

            val cityLat = edtCityLat.getText().trim()
            val cityLng = edtCityLng.getText().trim()
            if (validateLatLng(cityLat, cityLng)) {
                isSearchCalled = true
                searchWeatherByCityAndLocation()
            }
        }
    }

    private fun handleStateChange(state: WeatherState) {
        when (state) {
            is WeatherState.Init -> Unit
            is WeatherState.Success -> handleSuccessCitySearch()
            is WeatherState.SuccessWeather -> handleSuccess(state.mModel)
            is WeatherState.ShowToast -> mainLayout.snack(state.message) {}
            is WeatherState.IsLoading -> handleLoading(state.isLoading)
            is WeatherState.ShowResIdToast -> {
                mainLayout.snack(state.resId) {}
            }
        }
    }

    private fun handleSuccess(mModel: WeatherModel?) {
        mModel?.let {
            cslTempDetail.visible()
            it.mainWeatherInfo?.let { mainWeatherInfoModel ->
                tvTemp.text = mainWeatherInfoModel.temperature.roundToInt().toString()
                tvTempMax.text = mainWeatherInfoModel.maxTemperature.roundToInt().toString()
                tvTempMin.text = mainWeatherInfoModel.minTemperature.roundToInt().toString()
            }
            it.weatherInfo?.let { list ->
                if (list.isNotEmpty()) {
                    tvWindType.text = list[0].weatherParametersType
                    val weatherIconUrl =
                        BuildConfig.WEATHER_ICON_BASE_URL + list[0].weatherIcon + "@4x.png"
                    imgWeatherIcon.loadImage(weatherIconUrl)

                }
            }
            tvCityName.text = it.cityName
            edtCityLat.setText(it.coordinationModel?.lat.toString())
            edtCityLng.setText(it.coordinationModel?.lng.toString())

            if (!isSearchCalled) {
                edtCityName.setText(it.cityName.toString())
                edtCityLat.setText(it.coordinationModel?.lat.toString())
                edtCityLng.setText(it.coordinationModel?.lng.toString())
            }

        }
    }

    private fun handleSuccessCitySearch() {
        val cityName = edtCityName.getText().trim()
        if (cityName.isNotEmpty())
            searchWeatherByCityAndLocation()
    }

    private fun handleLoading(isLoading: Boolean) {
        if (isAdvanceSearchClick)
            btnSearchWeather.setLoading(isLoading)
        else
            pbCityNameSearch.visibility(isLoading)
    }

    private fun checkCityName() {
        viewModel.checkCityName(edtCityName.getText())
    }


    private fun validate(cityName: String): Boolean {
        val (resultBoolean, resultResId) = validate.validateCityName(
            cityName = cityName
        )

        when (resultBoolean) {
            true -> {
                edtCityName.showError(false)
            }
            false -> {
                resultResId?.let {
                    edtCityName.showError(true)
                    edtCityName.setErrorText(getString(it))
                }
            }
        }
        return resultBoolean
    }

    private fun validateLatLng(lat: String, lng: String): Boolean {
        val (resultBoolean, resultResId) = validate.validateCityLocation(
            lat = lat, lng
        )

        when (resultBoolean) {
            true -> {
                edtCityLat.showError(false)
                edtCityLng.showError(false)
            }
            false -> {
                resultResId?.let {
                    edtCityLng.showError(true)
                    edtCityLng.setErrorText(getString(it))
                    edtCityLat.showError(true)
                    edtCityLat.setErrorText(getString(it))
                }
            }
        }
        return resultBoolean
    }

    override fun onNetworkConnected() {
        searchWeatherByCityAndLocation()
    }

    override fun onNetworkDisconnected() {

    }

    private fun registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context?.registerReceiver(
                networkReceiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )
        }
    }

    override fun onStart() {
        super.onStart()
        try {
            registerNetworkBroadcastForNougat()
        } catch (e: Exception) {
        }
    }

    override fun onStop() {
        try {
            context?.updateWidget()
            context?.unregisterReceiver(networkReceiver)
        } catch (e: Exception) {
        }
        super.onStop()
    }
}

package ir.pourahmadi.weather.presentation.ui.main.home.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ir.pourahmadi.weather.R
import ir.pourahmadi.weather.data.remote.dto.weather.WeatherRequest
import ir.pourahmadi.weather.domain.model.news.WeatherModel
import ir.pourahmadi.weather.presentation.common.*
import ir.pourahmadi.weather.presentation.ui.base.MainBaseFragment
import ir.pourahmadi.weather.presentation.ui.main.home.viewModels.HomeViewModel
import ir.pourahmadi.weather.presentation.ui.main.home.viewModels.WeatherState
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_custom_edittext.view.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : MainBaseFragment(R.layout.fragment_home) {

    private var isAdvanceSearchClick: Boolean = false
    val viewModel: HomeViewModel by viewModels()
    private lateinit var request: WeatherRequest

    private val cityNameWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            if (edtCityName.getText().contains(" ")) {
                edtCityName.setText(s.toString().replace(" ", "_"))
                edtCityName.getText().let { edtCityName.setSelection(it) }
            }
            val cityName = edtCityName.getText().trim { it <= ' ' }

            if (validate(cityName)) {
                checkCityName()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clicks()
        getData()
        observe()
    }


    private fun getData() {
        val cityName = edtCityName.getText().trim()
        val cityLat = edtCityLat.getText().trim()
        val cityLng = edtCityLng.getText().trim()
        if (isAdvanceSearchClick)
            request = WeatherRequest(cityLat, cityLng, "")
        else
            request = WeatherRequest("", "", cityName)
        viewModel.getWeatherList(request)
    }


    private fun observe() {
        viewModel.mState
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }


    private fun clicks() {

        edtCityName.edtCustome.addTextChangedListener(cityNameWatcher)

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
                getData()
            }
        }
    }

    private fun handleStateChange(state: WeatherState) {
        when (state) {
            is WeatherState.Init -> Unit
            is WeatherState.SuccessWeatherList -> handleSuccess(state.mModel)
            is WeatherState.ShowToast -> mainLayout.snack(state.message) {}
            is WeatherState.IsLoading -> handleLoading(state.isLoading)
            is WeatherState.ShowResIdToast -> {
                mainLayout.snack(state.resId) {}
            }

        }

    }

    private fun handleSuccess(mModel: WeatherModel) {
    }

    private fun handleLoading(isLoading: Boolean) {
        if (isAdvanceSearchClick)
            btnSearchWeather.setLoading(isLoading)
        else
            pbHome.visibility(isLoading)
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


}

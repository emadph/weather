package ir.pourahmadi.weather.presentation.ui.base

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.pourahmadi.weather.utils.Validate
import javax.inject.Inject

@AndroidEntryPoint
abstract class MainBaseFragment(
    layoutId: Int
) : BaseFragment(layoutId) {
    lateinit var navController: NavController
    @set:Inject
    lateinit var validate: Validate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navController = findNavController()
    }
}
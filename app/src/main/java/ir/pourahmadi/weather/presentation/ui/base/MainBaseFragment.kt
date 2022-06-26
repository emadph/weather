package ir.pourahmadi.weather.presentation.ui.base

import android.content.Context
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import ir.pourahmadi.weather.presentation.ui.main.MainShareViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class MainBaseFragment(
    layoutId: Int
) : BaseFragment(layoutId) {
    lateinit var navController: NavController
    val shareViewModel: MainShareViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navController = findNavController()
    }
}
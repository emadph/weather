package ir.pourahmadi.weather.presentation.components

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.ViewCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ir.pourahmadi.weather.R
import ir.pourahmadi.weather.presentation.ui.main.MainShareViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {
    val shareViewModel: MainShareViewModel by activityViewModels()
    lateinit var navController: NavController

    override fun getTheme(): Int {
        return R.style.BaseBottomSheetDialog
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navController = findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(getLayoutId(), container, false)
        ViewCompat.setLayoutDirection(view, ViewCompat.LAYOUT_DIRECTION_LTR)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        afterView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        destroyView()
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun beforeView()

    abstract fun afterView()

    protected abstract fun destroyView()

}
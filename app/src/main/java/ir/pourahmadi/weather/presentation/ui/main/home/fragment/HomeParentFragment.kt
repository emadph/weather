package ir.pourahmadi.weather.presentation.ui.main.home.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import ir.pourahmadi.weather.R
import ir.pourahmadi.weather.domain.model.topic.TopicModel
import ir.pourahmadi.weather.presentation.common.snack
import ir.pourahmadi.weather.presentation.ui.base.MainBaseFragment
import ir.pourahmadi.weather.presentation.ui.main.home.adapter.ViewPagerAdapter
import ir.pourahmadi.weather.presentation.ui.main.home.viewModels.HomeParentState
import ir.pourahmadi.weather.presentation.ui.main.home.viewModels.HomeParentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home_parent.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class HomeParentFragment : MainBaseFragment(R.layout.fragment_home_parent) {

    private lateinit var vpAdapter: ViewPagerAdapter
    private val viewModel: HomeParentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        click()
        observe()
    }

    private fun observe() {
        viewModel.mState
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun click() {
    }

    private fun init() {
        trHome.apply {
            haveNotification(true)
            imgToolbarProfile.setOnClickListener {
                mainLayout.snack("کلیک") {}
            }
        }

        vpAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = vpAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = vpAdapter.getPageTitle(position)
        }.attach()

    }


    private fun handleStateChange(state: HomeParentState) {
        when (state) {
            is HomeParentState.Init -> Unit
            is HomeParentState.SuccessTopicList -> handleSuccess(state.mModel)
            is HomeParentState.ShowToast -> mainLayout.snack(state.message) {}
            is HomeParentState.IsLoading -> handleLoading(state.isLoading)
            is HomeParentState.ShowResIdToast -> {
                mainLayout.snack(state.resId) {}
            }
        }
    }

    private fun handleSuccess(resp: List<TopicModel>) {
        vpAdapter.clear()
        resp.forEach {
            vpAdapter.addFragment(
                HomeSubParentFragment.newInstance(it.topics, it.link, it.id),
                it.title
            )
        }
        vpAdapter.notifyItemRangeRemoved(0, resp.size)
    }

    private fun handleLoading(isLoading: Boolean) {
    }

}

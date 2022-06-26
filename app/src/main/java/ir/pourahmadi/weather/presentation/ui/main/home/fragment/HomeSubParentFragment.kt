package ir.pourahmadi.weather.presentation.ui.main.home.fragment

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import ir.pourahmadi.weather.R
import ir.pourahmadi.weather.domain.model.topic.TopicModel
import ir.pourahmadi.weather.presentation.common.gone
import ir.pourahmadi.weather.presentation.common.visible
import ir.pourahmadi.weather.presentation.ui.base.MainBaseFragment
import ir.pourahmadi.weather.presentation.ui.main.home.adapter.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home_sub_parent.*


@AndroidEntryPoint
class HomeSubParentFragment : MainBaseFragment(R.layout.fragment_home_sub_parent) {

    private lateinit var vpAdapter: ViewPagerAdapter
    var topicModel: List<TopicModel>? = null
    var urlLink: String? = null
    var tabId: Int = 0

    companion object {
        fun newInstance(
            topicModel: List<TopicModel>?,
            urlLink: String?,
            Id: Int
        ): HomeSubParentFragment {
            return HomeSubParentFragment().apply {
                tabId = Id
                this.urlLink = urlLink
                this.topicModel = topicModel
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initData()
    }

    private fun init() {
        vpAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = vpAdapter
        TabLayoutMediator(subTabLayout, viewPager) { tab, position ->
            tab.text = vpAdapter.getPageTitle(position)
        }.attach()
    }

    private fun initData() {
        topicModel?.let { it ->
            if (it.isNotEmpty()) {
                subTabLayout.visible()
                vDivider.visible()
                it.forEach { item ->
                    vpAdapter.addFragment(
                        HomeFragment.newInstance(item.link.toString()),
                        item.title
                    )
                }
            } else {
                vDivider.gone()
                subTabLayout.gone()
                vpAdapter.addFragment(HomeFragment.newInstance(urlLink!!), "")
            }

            vpAdapter.notifyItemRangeRemoved(0, it.size)
        }
    }

}

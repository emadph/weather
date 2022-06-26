package ir.pourahmadi.weather.presentation.ui.main.home.fragment

import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.pourahmadi.weather.domain.common.ARG_OBJECT
import ir.pourahmadi.weather.domain.model.Wearher.*
import ir.pourahmadi.weather.presentation.common.snack
import ir.pourahmadi.weather.presentation.common.visibility
import ir.pourahmadi.weather.presentation.ui.base.MainBaseFragment
import ir.pourahmadi.weather.presentation.ui.main.home.adapter.WearherListAdapter
import ir.pourahmadi.weather.presentation.ui.main.home.adapter.onClicks.WearherClickListener
import ir.pourahmadi.weather.presentation.ui.main.home.adapter.onClicks.WearherTypeClick
import ir.pourahmadi.weather.presentation.ui.main.home.viewModels.HomeViewModel
import ir.pourahmadi.weather.presentation.ui.main.home.viewModels.WearherState
import dagger.hilt.android.AndroidEntryPoint
import ir.pourahmadi.weather.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class HomeFragment : MainBaseFragment(R.layout.fragment_home), WearherClickListener {

    var url: String = ""
    val viewModel: HomeViewModel by viewModels()
    private val adapters by lazy { WearherListAdapter() }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false


    companion object {
        fun newInstance(WearherUrl: String): HomeFragment {
            return HomeFragment().apply {
                this.url = WearherUrl
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initWearherList()
        getData()
        observe()
    }


    private fun getData() {
        if (url.isNotEmpty()) {
            viewModel.getWearherList(url)
        }
    }


    private fun observe() {
        viewModel.mState
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initWearherList() {
        rvWearher.apply {
            adapter = adapters
            addOnScrollListener(this@HomeFragment.scrollListener)
        }

        adapters.setListener(this)
    }

    private fun init() {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            url = getInt(ARG_OBJECT).toString()
        }
    }

    private fun handleStateChange(state: WearherState) {
        when (state) {
            is WearherState.Init -> Unit
            is WearherState.SuccessWearherList -> handleSuccess(state.mModel)
            is WearherState.ShowToast -> mainLayout.snack(state.message) {}
            is WearherState.IsLoading -> handleLoading(state.isLoading)
            is WearherState.ShowResIdToast -> {
                mainLayout.snack(state.resId) {}
            }

        }

    }

    private fun handleSuccess(mModel: List<WearherListModel>) {
        isLastPage = mModel.isNullOrEmpty()

        adapters.differ.submitList(mModel.toList())
    }

    private fun handleLoading(isLoading: Boolean) {
        this.isLoading = isLoading
        pbHome.visibility(isLoading)
    }

    private fun openMoreBottomSheet() {
        navController.navigate(R.id.action_HomeParentFragment_to_WearherMoreBottomSheet)
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= 1
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling
            if (shouldPaginate) {
                viewModel.getWearherList(url)
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, Wearhertate: Int) {
            super.onScrollStateChanged(recyclerView, Wearhertate)
            if (Wearhertate == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }

    override fun onItemClickSimple(click: WearherTypeClick<SimpleModel>) {
        when (click) {
            is WearherTypeClick.onItemClick -> {
                mainLayout.snack("Item") {}
            }
            is WearherTypeClick.onItemClickMore -> {
                shareViewModel.setWearherModel(WearherListModel(click.value))
                openMoreBottomSheet()
            }
            is WearherTypeClick.onItemClickBookmark -> {
                mainLayout.snack("Bookmark") {}
            }
            is WearherTypeClick.onItemClickLike -> {
                mainLayout.snack("Like") {}
            }
        }
    }

    override fun onItemClickGallery(click: WearherTypeClick<GalleryModel>) {
        when (click) {
            is WearherTypeClick.onItemClick -> {
                mainLayout.snack("Item") {}
            }
            is WearherTypeClick.onItemClickMore -> {
                shareViewModel.setWearherModel(WearherListModel(null, null, click.value))
                openMoreBottomSheet()
            }
            is WearherTypeClick.onItemClickBookmark -> {
                mainLayout.snack("Bookmark") {}
            }
            is WearherTypeClick.onItemClickLike -> {
                mainLayout.snack("Like") {}
            }
        }

    }

    override fun onItemClickSlider(click: WearherTypeClick<BannerModel>) {
        when (click) {
            is WearherTypeClick.onItemClick -> {
                mainLayout.snack("Item") {}
            }
            is WearherTypeClick.onItemClickBookmark -> {
                mainLayout.snack("Bookmark") {}
            }
            is WearherTypeClick.onItemClickLike -> {
                mainLayout.snack("Like") {}
            }
        }
    }

    override fun onItemClickVideo(click: WearherTypeClick<VideoModel>) {
        when (click) {
            is WearherTypeClick.onItemClick -> {
                mainLayout.snack("Item") {}
            }
            is WearherTypeClick.onItemClickMore -> {
                shareViewModel.setWearherModel(WearherListModel(null, click.value))
                openMoreBottomSheet()
            }
            is WearherTypeClick.onItemClickBookmark -> {
                mainLayout.snack("Bookmark") {}
            }
            is WearherTypeClick.onItemClickLike -> {
                mainLayout.snack("Like") {}
            }
        }

    }


}

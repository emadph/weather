package ir.pourahmadi.weather.presentation.ui.main.home.adapter.WearherViewHolder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import ir.pourahmadi.weather.R
import ir.pourahmadi.weather.presentation.components.ProgressButton

class LoadStateWearherViewHolder constructor(
    itemView: View, retry: () -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val btnRetry: ProgressButton = itemView.findViewById(R.id.btnRetry)

    init {
        btnRetry.setOnClickListener {
            retry()
        }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Loading) {
            btnRetry.setLoading(true)
        } else {
            btnRetry.setLoading(false)
        }
    }

    companion object {
        fun getInstance(parent: ViewGroup, retry: () -> Unit): LoadStateWearherViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.item_Wearher_loader, parent, false)
            return LoadStateWearherViewHolder(view, retry)
        }
    }

}
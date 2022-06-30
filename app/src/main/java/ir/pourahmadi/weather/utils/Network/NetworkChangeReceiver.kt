package ir.pourahmadi.weather.utils.Network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class NetworkChangeReceiver(onNetworkListener: OnNetworkListener): BroadcastReceiver() {

    private var networkCallback : OnNetworkListener = onNetworkListener

    override fun onReceive(context: Context?, intent: Intent?) {
        if (!Network.isOnline(context)) {
            networkCallback.onNetworkDisconnected()
        } else {
            networkCallback.onNetworkConnected()
        }
    }

}
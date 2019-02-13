package archtree.helper

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.RequiresPermission
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectivityHelper
@Inject constructor() {

    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun isOnline(context: Context?): Boolean {
        return context?.run {
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            //should check null because in airplane mode it will be null
            netInfo != null && netInfo.isConnected
        } ?: false
    }
}
package archtree.helper

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresPermission

class ConnectivityHelper(private val context: Context) {

    /**
     * Get the current device network state.
     *
     * @return True if the device is online, false otherwise.
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    @Suppress("DEPRECATION")
    fun isOnline(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager?
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            cm?.activeNetworkInfo?.isConnected ?: false
        } else {
            val an = cm?.activeNetwork ?: return false
            val capabilities = cm.getNetworkCapabilities(an) ?: return false
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        }
    }
}
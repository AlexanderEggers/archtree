package archtree.list

import android.databinding.ObservableList
import android.databinding.adapters.ListenerUtil
import android.view.View

class BindableListUtil {

    companion object {

        fun <T> initialiseListBinding(oldItems: List<T>?,
                                      newItems: List<T>?,
                                      listener: OnListChangedCallbackAdapter<T>?,
                                      adapter: BindableListAdapter,
                                      container: View) {

            val oldItemsObs = if (oldItems is ObservableList<*>) oldItems as? ObservableList<T> else null
            val newItemsObs = if (newItems is ObservableList<*>) newItems as? ObservableList<T> else null

            var adapterListener = listener

            if (oldItems != newItems && oldItemsObs != null && listener != null) {
                oldItemsObs.removeOnListChangedCallback(listener)
            }

            if (adapterListener == null) {
                adapterListener = OnListChangedCallbackAdapter(adapter)
                ListenerUtil.trackListener(container, listener, R.id.listChangedListener)
            }

            if (newItems !== oldItems && newItemsObs != null) {
                newItemsObs.addOnListChangedCallback(adapterListener)
            }
        }
    }
}
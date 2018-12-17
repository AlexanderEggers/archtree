package archtree.testing

import android.arch.lifecycle.*
import org.powermock.api.mockito.PowerMockito

import java.util.Arrays

fun <T> liveDataOfList(list: List<T>): LiveData<List<T>> {
    val data = MutableLiveData<List<T>>()
    data.value = list
    return data
}

@SafeVarargs
fun <T> liveDataOfList(vararg values: T): LiveData<List<T>> {
    return liveDataOfList(Arrays.asList(*values))
}

fun <T> liveDataOf(value: T): LiveData<T> {
    val data = MutableLiveData<T>()
    data.value = value
    return data
}

fun <T> rawLiveDataOf(value: T): MediatorLiveData<T> {
    val data = MediatorLiveData<T>()
    data.value = value
    return data
}

fun <T> observeLiveData(liveData: LiveData<T>) {
    val lifecycle = LifecycleRegistry(PowerMockito.mock(LifecycleOwner::class.java))
    lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    liveData.observe({lifecycle}) {
        //do nothing - this observer will only be used to fake layout activity
    }
}
package archtree.helper

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimeHelper @Inject constructor() {

    fun currentTimeMillis(): Long {
        return System.currentTimeMillis()
    }
}
package archtree.testing.mockito

import kotlin.reflect.KClass

inline fun <reified T : Any> createInstance(): T {
    return createInstance(T::class)
}

fun <T : Any> createInstance(kClass: KClass<T>): T {
    return castNull()
}

@Suppress("UNCHECKED_CAST")
private fun <T> castNull(): T = null as T
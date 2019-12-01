package archtree.testing.mockito

import kotlin.reflect.KClass

inline fun <reified T : Any> createInstance(): T = createInstance(T::class)

@Suppress("UNUSED_PARAMETER")
fun <T : Any> createInstance(kClass: KClass<T>): T = castNull()

@Suppress("UNCHECKED_CAST")
private fun <T> castNull(): T = null as T
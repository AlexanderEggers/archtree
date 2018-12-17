package archtree.testing.mockito

import org.mockito.ArgumentCaptor
import org.mockito.Mockito

/**
 * Returns ArgumentCaptor.capture() as nullable type to avoid java.lang.IllegalStateException
 * when null is returned.
 */
inline fun <reified T : Any> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture() ?: createInstance()

/**
 * Returns Mockito.any() as nullable type to avoid java.lang.IllegalStateException when
 * null is returned.
 */
fun <T> any(): T = Mockito.any<T>()

inline fun <reified T : Any> argumentCaptor() = ArgumentCaptor.forClass(T::class.java)
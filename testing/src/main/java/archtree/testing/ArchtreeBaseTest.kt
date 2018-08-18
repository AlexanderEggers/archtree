package archtree.testing

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.os.Bundle
import archtree.helper.ResourceAccessor
import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito.mock

abstract class ArchtreeBaseTest {

    val resourceAccessor: ResourceAccessor = mock(ResourceAccessor::class.java)
    val bundleMock: Bundle = mock(Bundle::class.java)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockResultHelper.mockResourceAccessor(resourceAccessor)
    }

    /**
     * Should include all relevant test initialisations and mock creations.
     *
     *
     * <u>Example:</u>
     *
     *
     * when(repository.getData("myString")).thenReturn(...);
     *
     *
     * viewModel = new MyViewModel(...);
     * });
     */
    abstract fun initialiseTestData()
}

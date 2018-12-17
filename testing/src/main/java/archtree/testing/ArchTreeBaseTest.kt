package archtree.testing

import archtree.helper.ResourceAccessor
import org.junit.Before
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

abstract class ArchTreeBaseTest {

    val resourceAccessorMock: ResourceAccessor = mock(ResourceAccessor::class.java)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mockResourceAccessor(resourceAccessorMock)
    }

    /**
     * Should include all relevant test initialisations and mock creations.
     *
     * <u>Example:</u>
     *
     * when(repository.getData("myString")).thenReturn(...);
     * viewModel = new MyViewModel(...);
     */
    abstract fun initialiseTestData()
}

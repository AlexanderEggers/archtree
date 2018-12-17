package archtree.testing

import org.junit.Before
import org.mockito.MockitoAnnotations

abstract class ArchTreeBaseTest {

    @Before
    open fun setup() = MockitoAnnotations.initMocks(this)

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

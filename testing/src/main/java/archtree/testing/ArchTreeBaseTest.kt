package archtree.testing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations

abstract class ArchTreeBaseTest {

    @Rule @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    open fun setup() = MockitoAnnotations.initMocks(this)
}

package org.demo.archtree

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import archknife.context.ContextProviderCommunicator
import archtree.testing.mockito.capture
import autotarget.target.FragmentTarget
import autotarget.target.TargetService
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.reflect.Whitebox

@RunWith(PowerMockRunner::class)
class DemoActivityViewModelTest {

    @Mock
    private lateinit var targetServiceMock: TargetService

    @Mock
    private lateinit var contextProvider: ContextProviderCommunicator

    private lateinit var viewModel: DemoActivityViewModel

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() = MockitoAnnotations.initMocks(this)

    @Test
    fun testOnActionClick() {
        Whitebox.setInternalState(targetServiceMock, "contextProvider", contextProvider)

        initialiseTestData()
        viewModel.action.forceClick()

        val fragmentTargetArgumentCaptor: ArgumentCaptor<FragmentTarget> = ArgumentCaptor.forClass(FragmentTarget::class.java)
        verify(targetServiceMock).execute(capture(fragmentTargetArgumentCaptor))

        val fragmentTarget = fragmentTargetArgumentCaptor.value
        assertEquals(R.id.fragment_container, fragmentTarget.containerId)
    }

    private fun initialiseTestData() {
        viewModel = DemoActivityViewModel(targetServiceMock)
    }
}
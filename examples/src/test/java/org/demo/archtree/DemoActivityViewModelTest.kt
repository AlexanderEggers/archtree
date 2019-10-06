package org.demo.archtree

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import archknife.context.ContextProviderCommunicator
import archtree.testing.mockito.capture
import autotarget.service.FragmentTarget
import autotarget.service.TargetService
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.powermock.reflect.Whitebox

class DemoActivityViewModelTest {

    @Mock
    private lateinit var targetServiceMock: TargetService

    @Mock
    private lateinit var contextProviderCommunicator: ContextProviderCommunicator

    private lateinit var viewModel: DemoActivityViewModel

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() = MockitoAnnotations.initMocks(this)

    @Test
    fun testOnActionClick() {
        Whitebox.setInternalState(targetServiceMock, "contextProvider", contextProviderCommunicator)

        initialiseTestData()
        viewModel.action.forceClick()

        val fragmentTargetArgumentCaptor: ArgumentCaptor<FragmentTarget> = ArgumentCaptor.forClass(FragmentTarget::class.java)
        verify(targetServiceMock).execute(capture(fragmentTargetArgumentCaptor), anyInt(), any())

        val fragmentTarget = fragmentTargetArgumentCaptor.value
        assertEquals(2131230827, fragmentTarget.containerId)
        assertEquals(-1, fragmentTarget.state)
        assertEquals("undefined", fragmentTarget.tag)
    }

    private fun initialiseTestData() {
        viewModel = DemoActivityViewModel(targetServiceMock)
    }
}
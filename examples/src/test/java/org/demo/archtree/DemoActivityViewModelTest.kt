package org.demo.archtree

import archknife.context.ContextProviderCommunicator
import archtree.testing.ArchTreeBaseTest
import archtree.testing.mockito.capture
import autotarget.service.FragmentTarget
import autotarget.service.TargetService
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.powermock.reflect.Whitebox

class DemoActivityViewModelTest: ArchTreeBaseTest() {

    @Mock
    private lateinit var targetServiceMock: TargetService

    @Mock
    private lateinit var contextProviderCommunicator: ContextProviderCommunicator

    private lateinit var viewModel: DemoActivityViewModel

    @Test
    fun testOnActionClick() {
        Whitebox.setInternalState(targetServiceMock, "contextProvider", contextProviderCommunicator)

        initialiseTestData()
        viewModel.action.forceClick()

        val fragmentTargetArgumentCaptor: ArgumentCaptor<FragmentTarget> = ArgumentCaptor.forClass(FragmentTarget::class.java)
        verify(targetServiceMock).execute(capture(fragmentTargetArgumentCaptor), anyInt(),
                anyBoolean(), anyBoolean(), any())

        val fragmentTarget = fragmentTargetArgumentCaptor.value
        assertEquals(2131165243, fragmentTarget.containerId)
        assertEquals(-1, fragmentTarget.state)
        assertEquals("undefined", fragmentTarget.tag)
    }

    override fun initialiseTestData() {
        viewModel = DemoActivityViewModel(targetServiceMock)
    }
}
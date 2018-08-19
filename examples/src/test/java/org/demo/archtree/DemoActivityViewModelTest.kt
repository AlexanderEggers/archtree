package org.demo.archtree

import archtree.testing.ArchTreeBaseTest
import archtree.testing.capture
import autotarget.service.FragmentTarget
import autotarget.service.TargetService
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.*

class DemoActivityViewModelTest: ArchTreeBaseTest() {

    @Mock
    private lateinit var targetServiceMock: TargetService

    private lateinit var viewModel: DemoActivityViewModel

    @Test
    fun testOnActionClick() {
        initialiseTestData()
        viewModel.action.execute(null)

        val fragmentTargetArgumentCaptor: ArgumentCaptor<FragmentTarget> = ArgumentCaptor.forClass(FragmentTarget::class.java)
        verify(targetServiceMock).execute(capture(fragmentTargetArgumentCaptor), anyInt(),
                anyBoolean(), anyBoolean())

        val fragmentTarget = fragmentTargetArgumentCaptor.value
        assertEquals(2131165242, fragmentTarget.containerId)
        assertEquals(-1, fragmentTarget.state)
        assertEquals("undefined", fragmentTarget.tag)
    }

    override fun initialiseTestData() {
        viewModel = DemoActivityViewModel(targetServiceMock)
    }
}
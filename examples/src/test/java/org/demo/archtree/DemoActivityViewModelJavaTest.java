package org.demo.archtree;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import archtree.testing.ArchTreeBaseTest;
import autotarget.service.FragmentTarget;
import autotarget.service.TargetService;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

public class DemoActivityViewModelJavaTest extends ArchTreeBaseTest {

    @Mock
    private TargetService targetServiceMock;

    private DemoActivityViewModel viewModel;

    @Test
    public void testDemo() {
        initialiseTestData();
        viewModel.getAction().execute(null);

        ArgumentCaptor<FragmentTarget> fragmentTargetArgumentCaptor = ArgumentCaptor.forClass(FragmentTarget.class);
        verify(targetServiceMock).execute(fragmentTargetArgumentCaptor.capture(), anyInt(),
                anyBoolean(), anyBoolean());

        FragmentTarget fragmentTarget = fragmentTargetArgumentCaptor.getValue();
        assertEquals(2131165242, fragmentTarget.getContainerId());
        assertEquals(-1, fragmentTarget.getState());
        assertEquals("undefined", fragmentTarget.getTag());
    }

    @Override
    public void initialiseTestData() {
        viewModel = new DemoActivityViewModel(targetServiceMock);
    }
}

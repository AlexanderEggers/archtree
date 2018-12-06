package org.demo.archtree;

import android.content.Context;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.powermock.reflect.Whitebox;

import archknife.context.ContextProvider;
import archtree.testing.ArchTreeBaseTest;
import autotarget.service.FragmentTarget;
import autotarget.service.TargetService;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;

public class DemoActivityViewModelJavaTest extends ArchTreeBaseTest {

    @Mock
    private TargetService targetServiceMock;

    private DemoActivityViewModel viewModel;

    @Test
    public void testDemo() {
        Whitebox.setInternalState(targetServiceMock, "contextProvider", ContextProvider.INSTANCE);

        initialiseTestData();
        viewModel.getAction().forceClick();

        ArgumentCaptor<FragmentTarget> fragmentTargetArgumentCaptor = ArgumentCaptor.forClass(FragmentTarget.class);
        verify(targetServiceMock).execute(fragmentTargetArgumentCaptor.capture(), anyInt(),
                anyBoolean(), anyBoolean(), (Context) isNull());

        FragmentTarget fragmentTarget = fragmentTargetArgumentCaptor.getValue();
        assertEquals(2131165243, fragmentTarget.getContainerId());
        assertEquals(-1, fragmentTarget.getState());
        assertEquals("undefined", fragmentTarget.getTag());
    }

    @Override
    public void initialiseTestData() {
        viewModel = new DemoActivityViewModel(targetServiceMock);
    }
}

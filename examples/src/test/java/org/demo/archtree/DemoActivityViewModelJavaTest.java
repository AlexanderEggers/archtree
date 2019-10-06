package org.demo.archtree;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;

import archknife.context.ContextProviderCommunicator;
import autotarget.service.FragmentTarget;
import autotarget.service.TargetService;
import kotlin.jvm.JvmField;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;

public class DemoActivityViewModelJavaTest {

    @Mock
    private TargetService targetServiceMock;

    @Mock
    private ContextProviderCommunicator contextProviderCommunicator;

    private DemoActivityViewModel viewModel;

    @Rule
    @JvmField
    public InstantTaskExecutorRule instantTaskExecutorRule;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDemo() {
        Whitebox.setInternalState(targetServiceMock, "contextProvider", contextProviderCommunicator);

        initialiseTestData();
        viewModel.getAction().forceClick();

        ArgumentCaptor<FragmentTarget> fragmentTargetArgumentCaptor = ArgumentCaptor.forClass(FragmentTarget.class);
        verify(targetServiceMock).execute(fragmentTargetArgumentCaptor.capture(), anyInt(), (Context) isNull());

        FragmentTarget fragmentTarget = fragmentTargetArgumentCaptor.getValue();
        assertEquals(2131230827, fragmentTarget.getContainerId());
        assertEquals(-1, fragmentTarget.getState());
        assertEquals("undefined", fragmentTarget.getTag());
    }

    private void initialiseTestData() {
        viewModel = new DemoActivityViewModel(targetServiceMock);
    }
}

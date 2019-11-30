package org.demo.archtree;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import archknife.context.ContextProviderCommunicator;
import autotarget.target.FragmentTarget;
import autotarget.target.TargetService;
import kotlin.jvm.JvmField;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
public class DemoActivityViewModelJavaTest {

    @Mock
    private TargetService targetServiceMock;

    @Mock
    private ContextProviderCommunicator contextProvider;

    private DemoActivityViewModel viewModel;

    @Rule
    @JvmField
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDemo() {
        Whitebox.setInternalState(targetServiceMock, "contextProvider", contextProvider);

        initialiseTestData();
        viewModel.getAction().forceClick();

        ArgumentCaptor<FragmentTarget> fragmentTargetArgumentCaptor = ArgumentCaptor.forClass(FragmentTarget.class);
        verify(targetServiceMock).execute(fragmentTargetArgumentCaptor.capture());

        FragmentTarget fragmentTarget = fragmentTargetArgumentCaptor.getValue();
        assertEquals(R.id.fragment_container, fragmentTarget.getContainerId());
    }

    private void initialiseTestData() {
        viewModel = new DemoActivityViewModel(targetServiceMock);
    }
}

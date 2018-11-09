package com.lappdance.mlappchallenge;

import android.content.ComponentName;
import android.content.Intent;
import android.widget.Button;

import com.lappdance.mlappchallenge.authentication.DiskBasedUserRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.shadows.ShadowActivity;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class SplashActivityTest {

    private ActivityController<SplashActivity> mController;

    @Mock
    private DiskBasedUserRepository mNoSession;

    @Mock
    private DiskBasedUserRepository mActiveSession;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        doReturn(false).when(mNoSession).loadLoginValue();
        doReturn(true).when(mActiveSession).loadLoginValue();

        mController = Robolectric.buildActivity(SplashActivity.class);
    }

    @After
    public void tearDown() {
        DiskBasedUserRepository.setInstance(null);
    }

    @Test
    public void onCreate_loadsView() {
        DiskBasedUserRepository.setInstance(mNoSession);
        mController.setup();

        final Button openButton = mController.get().findViewById(R.id.open);
        assertThat("no open button!",
                openButton, is(notNullValue()));
    }

    @Test
    public void onCreate_opensActivity() {
        DiskBasedUserRepository.setInstance(mActiveSession);
        mController.setup();

        assertOpenedAccountActivity();
    }

    @Test
    public void onCreate_finishesActivity() {
        DiskBasedUserRepository.setInstance(mActiveSession);
        mController.setup();

        assertActivityIsFinished();
    }

    @Test
    public void openButton_clicked_opensAccountActivity() {
        DiskBasedUserRepository.setInstance(mNoSession);
        mController.setup();

        final Button openButton = mController.get().findViewById(R.id.open);
        openButton.performClick();

        assertOpenedAccountActivity();
    }

    @Test
    public void openButton_clicked_finishesActivity() {
        DiskBasedUserRepository.setInstance(mNoSession);
        mController.setup();

        final Button openButton = mController.get().findViewById(R.id.open);
        openButton.performClick();

        assertActivityIsFinished();
    }

    @Test
    public void openButton_clicked_createsSession() {
        DiskBasedUserRepository.setInstance(mNoSession);
        mController.setup();

        final Button openButton = mController.get().findViewById(R.id.open);
        openButton.performClick();

        verify(mNoSession).login();
    }

    private void assertOpenedAccountActivity() {
        ShadowActivity shadowActivity = shadowOf(mController.get());
        final Intent intent = shadowActivity.getNextStartedActivity();
        assertThat("no Activity started on button press",
                intent, is(notNullValue()));

        final ComponentName name = intent.getComponent();
        assertThat("no Activity specified in intent",
                name, is(notNullValue()));
        assertThat("wrong Activity started",
                name.getClassName(), is(AccountActivity.class.getName()));

    }

    private void assertActivityIsFinished() {
        assertThat(mController.get().isFinishing(), is(true));
    }
}
